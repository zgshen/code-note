package com.zgshen.code.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {

    public static void main(String[] args) {
        new Thread(new ClientHandle(), "client thread").start();
    }
}

class ClientHandle implements Runnable {

    @Override
    public void run() {
        try {
            Selector selector = Selector.open();
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1", 8221));
            sc.register(selector, SelectionKey.OP_CONNECT);

            while (selector.isOpen()) {
                if (selector.select(10) == 0) continue;
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    final SelectionKey key = it.next();
                    it.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isConnectable()) {
                        sc = (SocketChannel) key.channel();
                        if (!sc.finishConnect()) continue;
                        // 注册一个链接来读数据，下一个循环 selector.select(10) 可能读到的就是这个
                        sc.register(selector,SelectionKey.OP_READ);
                        // 发送消息给服务端
                        doWrite(sc);
                    }

                    if (key.isReadable()) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if (readBytes > 0) {
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String body = new String(bytes, "UTF-8");
                            System.out.println("client receive data: " + body);
                            // 读完就可以关了
                            selector.close();
                        } else if (readBytes < 0) {
                            //对端链路关闭
                            key.cancel();
                            sc.close();
                            selector.close();
                        } else {}
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(SocketChannel sc) throws IOException {
        byte[] bytes = "client msg....".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if (!writeBuffer.hasRemaining())
            System.out.println("client send to server succeed.");
    }
}