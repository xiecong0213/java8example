package com.tutorial.java8.lambda.factorypattern;

/**
 * Created by xiecong on 16/6/15.
 */
public class MidSizeCar extends Car {

    public MidSizeCar() {
        System.out.println("MidSizeCar is craeted.");
    }

    @Override
    public String toString() {
        return "I'm MidSizeCar.";
    }
}
