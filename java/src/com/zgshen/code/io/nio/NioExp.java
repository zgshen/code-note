package com.zgshen.code.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;

public class NioExp {

    public static void main(String[] args) {
        new Thread(new ServerHandle(8221)).start();
        new Thread(new ClientHandle(), "client thread").start();
    }


    @Test
    public void readFileTest() {
        readFile();
    }

    /**
     * nio 读取文件
     * https://blogs.oracle.com/javamagazine/post/java-nio-nio2-buffers-channels-async-future-callback
     */
    public static void readFile() {
        var file = Path.of(NioExp.class.getResource("nio.txt").getPath());
        try (var channel = AsynchronousFileChannel.open(file)) {
            var buffer = ByteBuffer.allocate(100);
            var handle = new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("Bytes read: " + result);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            };
            channel.read(buffer, 0, buffer, handle);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
