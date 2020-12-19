package com.design.state;

/**
 * 状态模式
 * 对象在不同的状态下表现不同的行为
 */

/**
 * 状态接口
 */
public interface State {
    void doAction();
}

/**
 * 第一个状态
 */
class FirstState implements State{
    @Override
    public void doAction() {
        System.out.println("First state, do something...");
    }

    public String toString(){
        return "First State";
    }
}

/**
 * 第二个状态
 */
class SecondState implements State{
    @Override
    public void doAction() {
        System.out.println("Second state, do something...");
    }

    public String toString(){
        return "Second State";
    }
}

/**
 * 实际带状态对象
 */
class Context {
    private State state;//状态

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void doAction() {
        state.doAction();
    }
}

class Test {
    public static void main(String[] args) {
        Context context = new Context();//object

        context.setState(new FirstState());
        context.doAction();//object do somethind at first state
        System.out.println("context state is:" + context.getState().toString());

        context.setState(new SecondState());
        context.doAction();//object do somethind at second state
        System.out.println("context state is:" + context.getState().toString());
    }
}