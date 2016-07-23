package com.tutorial.java8.lambda.factorypattern;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by xiecong on 16/6/4.
 */
@FunctionalInterface
public interface CarFactory {

    Car createCar(EnumCarType type);

    static CarFactory init(Consumer<CarBuilder> carBuilderConsumer)
    {
        Map<EnumCarType,Supplier<? extends Car>> carMap = new HashMap<>();
        carBuilderConsumer.accept(carMap::put);
        return carType -> carMap.get(carType).get();
    }
}
