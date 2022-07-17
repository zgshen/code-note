package com.design.chain;

/**
 * 责任链模式
 * 常用于记录日志、检查权限或准备相关资源等等
 */
public class ResponsibleChain {

    public static void main(String[] args) {
        AbstractLogger LOG = Log.getChainOfLoggers();

        //LOG.logMessage(AbstractLogger.INFO, "This is an information.");
        LOG.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        //LOG.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}


abstract class AbstractLogger {
    //日志级别
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    //责任链中的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        if(this.level <= level) {
            write(message);
        }
        if(nextLogger !=null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}

class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

class Log {
    public static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }
}