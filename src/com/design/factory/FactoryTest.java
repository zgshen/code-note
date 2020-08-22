package com.design.factory;

/**
 * @author nathan
 * @date 2020/8/21 23:59
 * @desc FactoryTest
 */
public class FactoryTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("CIRCLE");//CIRCLE or RECTANGLE or SQUARE
        shape.draw();
    }
}
