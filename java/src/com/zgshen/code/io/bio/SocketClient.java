package com.zgshen.code.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8120;

    public static void send(String msg) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(msg);
            System.out.println("client receive msg：" + in.readLine());
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

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> SocketClient.send("123")).start();
        Thread.sleep(1000L);
        new Thread(() -> SocketClient.send("abc")).start();
        Thread.sleep(2000L);
        new Thread(() -> SocketClient.send("20220406")).start();
    }
}
