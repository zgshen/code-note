package com.design.factory.abst;

import com.design.factory.Shape;

/**
 * @author nathan
 * @date 2020/8/22 12:51
 * @desc AbstractFactory
 * 抽象工厂
 */
public abstract  class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
