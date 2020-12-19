package com.design.command;

/**
 * 命令模式
 * 将一个请求（命令）封装成一个对象，从而使您可以用不同的请求对客户进行参数化。
 * 不同的命令响应不同的结果
 */

import java.io.File;

/**
 * 命令模式
 */
public interface Command {
    void execute();
}

/**
 * 第一个命令对象
 */
class FirstCommand implements Command {
    private  Action action;
    public FirstCommand(Action action) {
        this.action = action;
    }
    @Override
    public void execute() {
        action.first();
    }
}

/**
 * 第二个命令对象
 */
class SecondCommand implements Command {
    private  Action action;
    public SecondCommand(Action action) {
        this.action = action;
    }
    @Override
    public void execute() {
        action.second();
    }
}

class Action {
    public void first() {
        System.out.println("Command executed.");
    }
    public void second() {
        System.out.println("Another command executed.");
    }
}

class Invoker {
    public void call(Command command){
        command.execute();
    }
}

class Test {
    public static void main(String[] args) {
        Action action = new Action();

        Invoker invoker = new Invoker();
        invoker.call(new FirstCommand(action));//命令
        invoker.call(new SecondCommand(action));//另一个命令
    }
}