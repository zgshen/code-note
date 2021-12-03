package com.design.factory.abst;

/**
 * 工厂模式，抽象工厂
 * 工厂是创建出一种产品，而抽象工厂是创建出一类产品
 */
public class AbstractFactory {
    public static void main(String[] args) {
        FemaleAnimalFactory femaleAnimalFactory = new FemaleAnimalFactory();
        femaleAnimalFactory.createCat().gender();

        MaleAnimalFactory maleAnimalFactory = new MaleAnimalFactory();
        maleAnimalFactory.createDog().gender();
    }
}

interface Animal {
    //动物的抽象，都会吃
    void eat();

    void gender();
}

abstract class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog like to eat meat.");
    }
}

abstract class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat like to eat fish.");
    }
}

class FemaleDog extends Dog {
    public void gender() {
        System.out.println("I am a female Dog");
    }
}

class FemaleCat extends Cat {
    public void gender() {
        System.out.println("I am a female Cat");
    }
}

class MaleDog extends Dog {
    public void gender() {
        System.out.println("I am a male Dog");
    }
}

class MaleCat extends Cat {
    public void gender() {
        System.out.println("I am a male Cat");
    }
}

//抽象工厂
interface AnimalFactory {
    Animal createDog();

    Animal createCat();
}

class FemaleAnimalFactory implements AnimalFactory {
    // 生产母狗和母猫
    @Override
    public Animal createDog() {
        return new FemaleDog();
    }

    @Override
    public Animal createCat() {
        return new FemaleCat();
    }
}

class MaleAnimalFactory implements AnimalFactory {
    // 生产公狗和公猫
    @Override
    public Animal createDog() {
        return new MaleDog();
    }

    @Override
    public Animal createCat() {
        return new MaleCat();
    }
}