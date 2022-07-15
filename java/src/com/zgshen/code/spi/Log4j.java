package com.zgshen.code.spi;

public class Log4j implements Log {
    @Override
    public void log(String info) {
        System.out.println("Log4j:" + info);
    }
}
