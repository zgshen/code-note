package com.design.factory;

/**
 * @author nathan
 * @date 2020/8/21 23:56
 * @desc ShapeFactory
 * 根据不同条件创建不同对象，实现统一接口的类生产工厂
 */
public class ShapeFactory {
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
