package com.zgshen.code.io.bio;

/**
 * 阻塞IO
 * 当用户线程发出 IO 请求之后，内核会去查看数据是否就绪，如果没有就绪就会等待数据就绪，
 * 而用户线程就会处于阻塞状态，用户线程交出 CPU。当数据就绪之后，内核会将数据拷贝到用户线程，
 * 并返回结果给用户线程，用户线程才解除 block 状态。
 */
public class BioExp {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> new SocketServer().run());
        thread.start();
        while (thread.getState() != Thread.State.RUNNABLE) Thread.yield();

        new Thread(() -> SocketClient.send("123")).start();
        Thread.sleep(1000L);
        new Thread(() -> SocketClient.send("abc")).start();
        Thread.sleep(2000L);
        new Thread(() -> SocketClient.send("20220406")).start();
    }

}
