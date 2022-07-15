package com.zgshen.code.io.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AioServer {

    public static void main(String[] args) {
        new Thread(new AsyncServerHandle(8222)).start();
    }

}

/**
 * 处理
 */
class AsyncServerHandle implements Runnable {

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncServerHandle(int port){
        this.port = port;
        try{
            //创建一个异步的服务端通道
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            //绑定端口
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port:"+ port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        //传递一个CompletionHandler实例来接收通知
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
        try{
            //允许当前线程阻塞，防止服务端执行完退出
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

/**
 * 接收通知
 */
class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandle> {

    @Override
    public void completed(AsynchronousSocketChannel result, AsyncServerHandle attachment) {
        //继续接收
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandle attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}

/**
 *
 */
class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel){
        if(this.channel == null){
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result,ByteBuffer attachment){
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try{
            String req = new String(body,"UTF-8");
            System.out.println("server receive data: " + req);
            doWrite("receive successful.");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    private void doWrite(String currentTime){
        if(currentTime !=null && currentTime.trim().length()>0){
            byte[] bytes = (currentTime).getBytes();
            final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    //如果没有发送完成，继续发送
                    if(buffer.hasRemaining())
                        channel.write(buffer,buffer,this);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try{
                        channel.close();
                    }catch (IOException e){
                        //ingnore on close
                    }
                }
            });
        }
    }

    public void failed(Throwable exc,ByteBuffer attachment){
        try{
            this.channel.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}