package com.zgshen.code.spi;

public class Logback implements Log {
    @Override
    public void log(String info) {
        System.out.println("Logback:" + info);
    }
}
