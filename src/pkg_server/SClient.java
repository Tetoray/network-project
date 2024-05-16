package pkg_server;

/**
 * tarik.alrayan
 * 2121221366
 */

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SClient extends Thread {

    // her clientın bir soketi olamlı
    public int id;
    public Socket socket;
    private Server server;
    // gönderilecek alınacak bilgileri byte dizisine çevirmek için
    private DataInputStream sInput;
    //private DataInputStream sInput;
    private DataOutputStream sOutput;

    public boolean isListening = false;

    private String key;

    private String name;

    //yapıcı metod
    public SClient(Socket socket, Server server) {

        try {
            this.server = server;
            this.socket = socket;
            this.sInput = new DataInputStream(this.socket.getInputStream());
            this.sOutput = new DataOutputStream(this.socket.getOutputStream());
            this.id = server.clientId;

        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getCname() {
        return name;
    }

    public void setCname(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void Listen() {
        this.isListening = true;
        this.start();

    }

    @Override
    public void run() {
        try {
            while (this.isListening) {
                byte[] messageByte = new byte[64];
                int bytesRead = sInput.read(messageByte);
                if (bytesRead == -1) {
                    // If bytesRead is -1, it means the stream has reached the end, 
                    // which usually happens when the socket is closed.
                    System.out.println("Socket closed. Stopping listening.");
                    this.isListening = false;
                    break;
                }
                String message = new String(messageByte, 0, bytesRead);
                System.out.println(message);
                Frm_Server.lst_messagesFromClient_model.addElement(this.socket.getPort() + "->" + message);

                // Check if the received message is a file command
                if (message.equals("file")) {
                    // Receive the file data
                    receiveAndBroadcastFile();
                } else if (message.startsWith("disconnected|")) {
                    // Extract the username
                    String[] parts = message.split("\\|");
                    String username = parts[1];

                    // Disconnect the client
                    this.Disconnect();

                    break; // Exit the loop
                } else {
                    // Broadcast other types of messages to clients
                    server.BroadCastMessageToRoom(messageByte, this.key);
                }
            }
        } catch (java.net.SocketException ex) {
            // Catch the SocketException and handle it gracefully
            System.out.println("Connection aborted: " + ex.getMessage());
            this.Disconnect(); // Disconnect the client

        } catch (IOException ex) {
            if (!socket.isClosed()) {
                this.Disconnect();
                Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);

            }
        } finally {
            // Ensure the socket is closed even if an exception occurs.
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //mesaj gönderme fonksiyonu
    public void SendMessage(byte[] msg) {
        try {
            sOutput.write(msg);
        } catch (IOException err) {
            System.out.println("Exception writing to server: " + err);
        }
    }

    public void receiveAndBroadcastFile() {
        try {
            // Create a ByteArrayOutputStream to store the file data
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Read file data from the input stream
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = sInput.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Get the file data as byte array
            byte[] fileData = baos.toByteArray();

            // Broadcast the file data to all clients in the same room
            server.BroadCastFileToRoom(fileData, this.key);

            System.out.println("File received and broadcasted successfully.");
        } catch (IOException e) {
            System.out.println("Error receiving file: " + e.getMessage());
        }
    }

    public void sendFile(byte[] fileData) {
        try {
            // Write the file data to the output stream
            sOutput.write(fileData);
            sOutput.flush(); // Flush the output stream to ensure all data is sent
            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.out.println("Error sending file: " + e.getMessage());
        }
    }

    //clientı kapatan fonksiyon
    public void Disconnect() {
        try {
            this.isListening = false;
            this.socket.close();
            this.sInput.close();
            this.sOutput.close();
            this.server.removeClient(this); // Notify the server to remove this client
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
