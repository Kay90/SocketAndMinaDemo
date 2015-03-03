package com.k.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by k on 2015/3/3.
 */
public class SocketServer {

    public static void main(String[] args){
        SocketServer socketServer = new SocketServer();
        socketServer.startServer();
    }

    BufferedWriter writer = null;
    BufferedReader reader = null;

    public void startServer(){
        ServerSocket serverSocket = null;

        //BufferedWriter writer = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(9000);
            System.out.println("Server started...");
            while (true){
                socket = serverSocket.accept();
                manageConnection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void manageConnection(final Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Client " + socket.hashCode() + " connection");
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String receivedMsg;
                    while((receivedMsg = reader.readLine())!=null){
                        System.out.println("Client " + socket.hashCode() + ": " + receivedMsg);
                        writer.write("Server reply: " + receivedMsg + "\n");
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        writer.close();
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
