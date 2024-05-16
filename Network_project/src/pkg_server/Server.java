package pkg_server;

/**
 * tarik.alrayan
 * 2121221366
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    // her clientın bir soketi olamlı
    private ServerSocket serverSocket;
    public boolean isListening = false;
    public int clientId = 0;

    // port numarası
    private int port;

    private ArrayList<SClient> clientList;


    //yapıcı metod
    public Server() {
       
    }

    // client başlatma
    public boolean Create(int port) {

        try {
            this.port = port;
            // Client Soket nesnesi
            serverSocket = new ServerSocket(this.port);
            clientList = new ArrayList<>();

        } catch (Exception err) {
            System.out.println("Error connecting to server: " + err);
        }
        return true;
    }

    @Override
    public void run() {
        try {
            // Server loop to accept client connections
            while (this.isListening) {
                System.out.println("Server waiting client...");
                Socket clientSocket = this.serverSocket.accept();//blocing
                SClient nsclient = new SClient(clientSocket, this);
                nsclient.Listen();
                clientList.add(nsclient);
                this.clientId++;
              
                // Read client information
                DataInputStream clientInput = new DataInputStream(clientSocket.getInputStream());
                String key = clientInput.readUTF();
                String name = clientInput.readUTF();
                nsclient.setKey(key);
                nsclient.setCname(name);

                // Update GUI with client information
                final String cinfo = nsclient.id + "|" + clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort() + "|" + key;
                Frm_Server.lst_clients_model.addElement(cinfo);

                // Send a message to all clients with the names of all online clients
                String onlineClientsMessage = constructOnlineClientsMessage(key);
                BroadCastMessageToRoom(onlineClientsMessage.getBytes(), key);

            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to remove a client from the server
    public void removeClient(SClient client) {
        this.clientList.remove(client);
        Frm_Server.lst_clients_model.removeAllElements();

        for (SClient sClient : clientList) {
            String cinfo = sClient.socket.getInetAddress().toString() + ":" + sClient.socket.getPort();
            Frm_Server.lst_clients_model.addElement(cinfo);

        }
        String onlineClientsMessage = constructOnlineClientsMessage(client.getKey());
        BroadCastMessageToRoom(onlineClientsMessage.getBytes(),client.getKey() );
    }

    // Method to start listening for client connections
    public void Listen() {
        this.isListening = true;
        this.start();

    }

    // Method to stop the server
    public void Stop() {
        try {
            this.isListening = false;
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to send a message to a specific client
    public void SendMessage(byte[] msg, int clientId) {
        msg[msg.length - 1] = 0x14;
        for (SClient sClient : this.clientList) {
            if (clientId == sClient.id) {
                sClient.SendMessage(msg);
                break;
            }
        }

    }

     // Method to broadcast a message to all clients in a room
    public void BroadCastMessageToRoom(byte[] msg, String key) {
        for (SClient sClient : this.clientList) {
            if (key.equals(sClient.getKey())) {
                sClient.SendMessage(msg);

            }
        }
    }

    // Method to broadcast a file to all clients in a room
    public void BroadCastFileToRoom(byte[] fileData, String key) {
        for (SClient sClient : this.clientList) {
            if (key.equals(sClient.getKey())) {
                sClient.sendFile(fileData);
            }
        }
    }

    // Method to send a broadcast message to all clients
    public void SendBroadCastMessage(byte[] msg) {
        for (SClient sClient : this.clientList) {
            msg[msg.length - 1] = 0x14;
            sClient.SendMessage(msg);
        }
    }

    // Method to construct a message containing the names of all online clients in a room
    private String constructOnlineClientsMessage(String key) {
        StringBuilder message = new StringBuilder("Online users: ");
        for (SClient client : clientList) {
            if(client.getKey().equals(key)){
                 message.append(client.getCname()).append(", ");
            }
           
        }
        // Remove the last comma and space
        if (clientList.size() > 0) {
            message.setLength(message.length() - 2);
        }
        return message.toString();
    }

}
