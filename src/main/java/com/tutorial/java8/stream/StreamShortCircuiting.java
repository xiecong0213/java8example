package com.tutorial.java8.stream;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by xiecong on 16/6/15.
 * <p>
 * A short-circuiting operation potentially allows processing of a stream to stop early without examining all the elements.
 * This is an especially desirable property when dealing with infinite streams;
 * if none of the operations being invoked on a stream are short-circuiting, then the code may never terminate.
 * </p>
 */
public class StreamShortCircuiting {
    public static void main(String[] args) {
        new Random().ints().findFirst().ifPresent(System.out::println);

        SecureRandom secureRandom = new SecureRandom(new byte[]{1, 3, 7, 11});
        int[] randoms = IntStream.generate(secureRandom::nextInt)
                .filter(n -> n > 0)
                .limit(10)
                .toArray();
        System.out.println(Arrays.toString(randoms));
    }
}
