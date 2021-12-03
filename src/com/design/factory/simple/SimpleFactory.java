package com.design.factory.simple;

/**
 * 简单工厂
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Animal dog = AnimalFactory.createAnimal("dog");
        dog.eat();

        Animal cat = AnimalFactory.createAnimal("cat");
        cat.eat();
    }
}

interface Animal {
    void eat();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog like to eat meat.");
    }
}

class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat like to eat fish.");
    }
}

class AnimalFactory {
    public static Dog createDog() {
        return new Dog();
    }

    public static Cat createCat() {
        return new Cat();
    }

    //静态函数。根据类型要狗还是要猫
    public static Animal createAnimal(String type) {
        if (type == null) return null;
        if ("dog".equals(type)) {
            return new Dog();
        } else if ("cat".equals(type)) {
            return new Cat();
        }
        return null;
    }
}


