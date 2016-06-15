package com.tutorial.java8.lambda.factorypattern;

/**
 * Created by xiecong on 16/6/4.
 */
public class MicroCar extends Car{

    public MicroCar() {
        System.out.println("MicroCar is craeted.");
    }

    @Override
    public String toString() {
        return "I'm MicroCar.";
    }
}
