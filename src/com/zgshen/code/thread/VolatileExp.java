package com.zgshen.code.thread;

/**
 * volatile 状态标记量应用
 */
public class VolatileExp {

    private static volatile boolean flag = false;

    public void otherThread() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            flag = true;
        }).start();
    }

    public static void main(String[] args) {
        VolatileExp exp = new VolatileExp();
        exp.otherThread();
        while (true) {
            if (exp.flag) {
                break;
            }
        }
        System.out.println("do something.");
    }
}
