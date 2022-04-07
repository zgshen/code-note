package com.zgshen.code.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8120);
            System.out.println("socket server has started.");
            while (true) {
                // 阻塞等待直到拿到一个链接FD
                Socket socket = server.accept();
                new Thread(new Handle(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.start();
    }
}

/**
 * 处理罗就
 */
class Handle implements Runnable {
    private Socket socket;
    public Handle(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String str = null;
            while (true) {
                if ((str = in.readLine()) == null) {
                    break;
                }
                System.out.println("server receive msg: " + str);
                out.println("receive successful.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) out.close();

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

