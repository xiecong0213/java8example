package com.tutorial.java8.lambda.factorypattern;

import java.util.function.Supplier;

/**
 * Created by xiecong on 16/6/4.
 */
public interface CarBuilder {
    void build(EnumCarType carType, Supplier<Car> supplier);
}
