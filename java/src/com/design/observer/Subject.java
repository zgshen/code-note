package com.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nathan
 * @date 2020/8/23 23:03
 * @desc Subject
 * 主题，被观察者
 */
public class Subject {

    private List<Observer> observerList = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();//状态变化通知观察者
    }

    public void attach(Observer observer){
        observerList.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observerList) {
            observer.update();
        }
    }

}
