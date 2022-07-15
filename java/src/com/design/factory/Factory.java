package com.design.factory;

/**
 * 工厂模式
 * 简单工厂在此基础上简化
 * 抽象工厂在此基础上扩展
 */
public class Factory {

    public static void main(String[] args) {
        AnimalFactory dogFactory = new DogFactory();
        //要只狗
        dogFactory.createAnimal().eat();

        AnimalFactory catFactory = new CatFactory();
        //要只猫
        catFactory.createAnimal().eat();

        //如何要只羊驼那就再创建个羊驼宠物店工厂好了
    }
}


interface Animal {
    //动物的抽象，都会吃
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

//宠物店的抽象，有许多不同的宠物卖的
interface AnimalFactory {
    Animal createAnimal();
}

//卖狗的宠物店
class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}

//卖猫的宠物店
class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
