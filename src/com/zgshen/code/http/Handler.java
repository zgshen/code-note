package com.zgshen.code.http;

import java.io.*;
import java.net.Socket;

/**
 * @author nathan
 * @date 2020/8/8 12:07
 * @desc Handler
 */
public class Handler implements Runnable {

    public Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            Request req = new Request(in);
            System.out.println(req);

            if ("/".equals(req.path)) {
                req.path = Handler.class.getResource("index.html").getPath();
            }
            if ("/favicon.ico".equals(req.path)) {
                req.path = Handler.class.getResource("favicon.ico").getPath();
            }
            File file = new File(req.path);
            InputStream fin = new FileInputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];

            OutputStream out = socket.getOutputStream();
            Response res = new Response(out, "HTTP/1.1", "200", "OK");
            res.writeHeader();
            while ((len = fin.read(buf)) > 0)
                res.writeBytes(buf, 0, len);

            socket.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
