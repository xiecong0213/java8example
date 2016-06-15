package com.tutorial.java8.lambda.factorypattern;

/**
 * Created by xiecong on 16/6/15.
 */
public class FullSizeCar extends Car{

    public FullSizeCar() {
        System.out.println("FullSizeCar is craeted.");
    }

    @Override
    public String toString() {
        return "I'm FullSizeCar.";
    }
}
