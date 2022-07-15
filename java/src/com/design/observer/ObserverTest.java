package com.design.observer;

/**
 * @author nathan
 * @date 2020/8/23 23:13
 * @desc ObserverTest
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexaObserver(subject);

        System.out.println("First state change: 2");
        subject.setState(2);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
