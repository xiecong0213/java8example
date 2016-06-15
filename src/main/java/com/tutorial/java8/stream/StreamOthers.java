package com.tutorial.java8.stream;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xiecong on 16/6/15.
 */
public class StreamOthers {
    public static void main(String[] args) {

        Stream.of(new BigDecimal("1.5"), new BigDecimal("2.5")).mapToDouble(BigDecimal::doubleValue).average().ifPresent(System.out::println);
        System.out.println(IntStream.range(0,10).sum());


        int reduced =
                IntStream.range(0, 10)
                        .reduce(7, (a, b) -> a + b);
        System.out.println(reduced);


        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

    }
}
