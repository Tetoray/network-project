package pkg_client;

/**
 * tarik.alrayan 2121221366
 */
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import network_project.gui.Main;

public class Client implements Runnable {

    
    // socket for every client
    private Socket socket;
    // gönderilecek alınacak bilgileri byte dizisine çevirmek için
    private DataInputStream sInput;
    //private DataInputStream sInput;
    private DataOutputStream sOutput;

    
    // server adresi 
    private String server;
    // port numarası
    private int port;
    //check if the client is Listening to server
    private boolean isListening = false;

    
    // the key of the joined project 
    private String key;
    //the name of the user
    private String name;

    
    //yapıcı metod
    public Client(String server, int port, String name) {

        this.server = server;
        this.port = port;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // handel the connection with the server
    public boolean ConnectToServer(String key) {

        try {
            
            socket = new Socket(this.server, this.port);

            sInput = new DataInputStream(socket.getInputStream());
            sOutput = new DataOutputStream(socket.getOutputStream());

            //pass the key and the name to the server
            sOutput.writeUTF(key);
            sOutput.writeUTF(name);

            this.Listen();
            
            System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
            
        } catch (Exception err) {
            System.out.println("Error connecting to server: " + err);
        }
        return true;
    }

    // handel  reciving messages from the server
    public void Listen() {
        if (isListening) {
            return;
        }
        this.isListening = true;
        Thread t1 = new Thread(this);
        t1.start();
        System.out.println("thread started ");

    }

    @Override
    public void run() {
        try {
            while (this.isListening) {
                
                byte[] messageByte = new byte[1024];
                int bytesRead = sInput.read(messageByte);
                String message = new String(messageByte, 0, bytesRead);
                
                if (bytesRead == -1) {
                    System.out.println("Socket closed. Stopping listening.");
                    this.isListening = false;
                    break;
                } 
                
                // this message has been send from the server to notify the client about the online clients on the same project
                else if (message.startsWith("Online users: ")) {
                    // Update the online users list
                    updateOnlineUsersList(message.substring(14));
                } 
                
                
                // handel reciving files from the server
                else if (message.equals("file")) {
                    receiveFile("new file");
                } 
                
                // handel reciving message from the server and add it to the message list 
                else {
                    System.out.println(message + " --- this message received to client ");
                    Main.lst_messagesFromServer_model.addElement(message);
                }
            }
        } catch (IOException ex) {
            if (!socket.isClosed()) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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

    // handel sending Message to the server
    public void SendMessage(String msg) {
        try {

            String sender = this.name;

            // Send the message bytes to the server
            String message = sender + " : " + msg;
            byte[] messageBytes = message.getBytes("UTF-8");
            sOutput.write(messageBytes);

        } catch (IOException err) {
            System.out.println("Exception writing to server: " + err);
        }
    }

    // handel disconnect client from the server
    public void disconnect() {
        try {
            // Send a disconnection message to the server along with the username
            sOutput.writeUTF("disconnected|" + this.name);

            // Close input, output streams, and the socket
            if (sInput != null) {
                sInput.close();
            }
            if (sOutput != null) {
                sOutput.close();
            }
            if (socket != null) {
                socket.close();
            }
            this.isListening = false;
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    // Method to receive a file based on the sender's sendFile method
    public void receiveFile(String fileName) {
        try {
            // Save the file data to a temporary location
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Read file data from the input stream
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = sInput.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Get the file data as byte array
            byte[] fileData = baos.toByteArray();

            // Create a temporary file to save the received file
            Path tempFilePath = Files.createTempFile(fileName, null);
            try (FileOutputStream fos = new FileOutputStream(tempFilePath.toFile())) {
                fos.write(fileData);
            }

            // Generate a download link for the file
            String downloadLink = "<a href=\"" + tempFilePath.toUri() + "\">Download " + fileName + "</a>";

            // Display the download link in the chat interface
            String message = "Received file: " + downloadLink;
            Main.lst_messagesFromServer_model.addElement(message);

            System.out.println("File received successfully.");
        } catch (IOException e) {
            System.out.println("Error receiving file: " + e.getMessage());
        }
    }

    // Method to send a file to server
    public void sendFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(fis)) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            // Send the "file" command to the server
            sOutput.writeUTF("file");

            // Send the file data
            while ((bytesRead = bis.read(buffer)) != -1) {
                sOutput.write(buffer, 0, bytesRead);
            }

            // Inform the server that the file transfer is complete
            sOutput.writeUTF("file_complete");

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.out.println("Error sending file: " + e.getMessage());
        }

    }

    // Method to update the online client list on the main frame whenever new client joins or disconnected
    private void updateOnlineUsersList(String onlineUsers) {
        String[] users = onlineUsers.split(",");
        // Clear the current online users list
        Main.lst_online_clients_model.clear();
        // Add the updated list of online users
        for (String user : users) {
            Main.lst_online_clients_model.addElement(user);
        }
    }

}
