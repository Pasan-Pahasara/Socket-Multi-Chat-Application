package lk.ijse.multiChatApplication.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/
public class ClientHandler extends Thread {
    private Socket socket;
    private ArrayList<ClientHandler> clients;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * Client Handler
     */
    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        try {
            this.socket = socket;
            this.clients = clients;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thread Was Constructed Using A Separate Runnable run Object
     */
    @Override
    public void run(){
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                if (message.equalsIgnoreCase("bye")) {
                    break;
                }
                for (ClientHandler handler : clients) {
                    handler.writer.println(message);
                }
            }
        } catch (SocketException e) {
            System.out.println("Error occurred in lk.ijse.multiChatApplication.main: " + e.getStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
