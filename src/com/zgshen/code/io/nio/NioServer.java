package com.zgshen.code.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public void start() {
        ServerHandle handle = new ServerHandle(8221);
        new Thread(handle, "server").start();
    }

    public static void main(String[] args) {
        NioServer server = new NioServer();
        server.start();
    }
}


class ServerHandle implements Runnable {

    private Selector selector;
    private ServerSocketChannel channel;

    public ServerHandle(int port) {
        try {
            // 创建选择器
            selector = Selector.open();
            // 打开通道
            channel = ServerSocketChannel.open();
            // false 为非阻塞
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port), 1024);
            channel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server has started.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (selector.select(1000) == 0) continue;
                // 获取到的是集合
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while(it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();
                    if (!key.isValid()) continue;
                    // 接入请求
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        // 注册一个链接来读数据，下一个循环 selector.select(1000) 可能读到的就是这个
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                    // 读数据并且发送数据
                    if (key.isReadable()) {
                        readData(key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = 0;
        try {
            readBytes = sc.read(readBuffer);
            if(readBytes > 0 ) {
                //将缓冲区当前的limit设置为position,position设置为0
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes, "UTF-8");
                System.out.println("server receive data: " + body);
                writeData(key);
            } else if (readBytes < 0) {
                key.cancel();
                sc.close();
            } else {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        String respStr = "receive successful.";
        byte[] bytes = respStr.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
    }

}
