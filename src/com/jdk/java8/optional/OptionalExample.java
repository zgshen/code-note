package com.jdk.java8.optional;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class OptionalExample {

    private Person person;
    private Car car;
    private Insurance insurance;

    @Before
    public void init() {
        insurance = new Insurance("Tesla");
        car = new Car(Optional.of(insurance));
        person = new Person(Optional.of(car));
    }

    @Test
    public void test1() {
        Optional<Insurance> insurance = Optional.ofNullable(this.insurance);
        Optional<String> s = insurance.map(insurance1 -> insurance1.getName());
        System.out.println(s);
    }

    @Test
    public void test2() {
        Optional<Person> person = Optional.of(this.person);
        String name = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("ubknow");
        System.out.println(name);
    }

    @Test
    public void test3() {
        Optional<Car> c = Optional.empty();
        Optional<String> s = c.flatMap(Car::getInsurance)
                .map(Insurance::getName);
        System.out.println(s);
        String unknow = s.orElse("unknow");
        System.out.println(unknow);
    }

}


class Person {
    private Optional<Car> car;

    public Person(Optional<Car> car) {
        this.car = car;
    }
    public Optional<Car> getCar() {
        return car;
    }
}


class Car {
    private Optional<Insurance> insurance;

    public Car(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

class Insurance {
    private String name;

    public Insurance(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}