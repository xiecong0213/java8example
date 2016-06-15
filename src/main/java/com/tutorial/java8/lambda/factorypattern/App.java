package com.tutorial.java8.lambda.factorypattern;

import java.util.stream.Stream;

/**
 * Created by xiecong on 16/6/4.
 * Factory-Pattern is a creational pattern which defines a factory of immutable content
 * with separated builder and factory interfaces to deal with the problem of
 * creating one of the objects specified directly in the factory-kit instance.
 *
 * <p>
 * In the given example {@link CarFactory} represents the factory-kit, that contains
 * four {@link CarBuilder}s for creating new objects of
 * the classes implementing {@link CarBuilder} interface.
 * <br>Each of them can be called with {@link CarFactory#createCar(EnumCarType)} method, with
 * an input representing an instance of {@link EnumCarType} that needs to
 * be mapped explicitly with desired class type in the factory instance.
 */
public class App {
    public static void main(String[] args) {
        CarFactory carFactory = CarFactory.init(carBuilder -> {
            carBuilder.build(EnumCarType.MICROCAR, MicroCar::new);
            carBuilder.build(EnumCarType.COMPACT_CAR, CompactCar::new);
            carBuilder.build(EnumCarType.MID_SIZE_CAR, MidSizeCar::new);
            carBuilder.build(EnumCarType.FULL_SIZE_CAR,FullSizeCar::new);
        });

        Stream.generate(() -> carFactory.createCar(EnumCarType.random())).limit(5).peek(x -> System.out.println(x.start())).forEach(System.out::println);

    }
}
