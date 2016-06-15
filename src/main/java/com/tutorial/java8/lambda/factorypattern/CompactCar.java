package com.tutorial.java8.lambda.factorypattern;

/**
 * Created by xiecong on 16/6/4.
 */
public class CompactCar extends Car{
    public CompactCar() {
        System.out.println("CompactCar is created.");
    }

    @Override
    public String toString() {
        return "I'm CompactCar.";
    }
}
