package lk.ijse.multiChatApplication.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class Server {
    private static final ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        final int PORT = 8000;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for clients...");
                Socket localSocket = serverSocket.accept();
                System.out.println("Port " + localSocket.getPort());
                System.out.println("IP " + localSocket.getInetAddress());
                System.out.println("Connected");

                ClientHandler clientThread = new ClientHandler(localSocket, clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
