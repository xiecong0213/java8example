package com.tutorial.java8.stream;

import com.tutorial.java8.lambda.InterfaceStaticMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiecong on 16/6/15.
 *
 * <p>
 *     The actions of a parallel stream may be happening all at once on multiple threads.
 *     parallel stream can promote performance,particular in big stream
 *     you can use vm param control parallel threadpool
 *     -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
 * </p>
 */
public class StreamParallel {

    public static final int MAX = 1000000;

    public static void testSortSequentialCost() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long startTime = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    public static void testsortParallelCost() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long startTime = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    public static void testParamllelThreadName(){
        List<Student> studentList = Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0),new Student("James",29,88.0));

        Integer ageSum = studentList.parallelStream().reduce(0,(sum,student)->{
            System.out.format("accumulator: sum=%s; person=%s; thread=%s\n",
                    sum, student, Thread.currentThread().getName());
            return sum+=student.getAge();
        },(sum1,sum2) -> {
            System.out.format("combiner: sum1=%s; sum2=%s; thread=%s\n",
                    sum1, sum2, Thread.currentThread().getName());
            return sum1 + sum2;
        });

        System.out.println("ageSum:" + ageSum);
    }

    public static void main(String[] args) {
        testSortSequentialCost();
        testsortParallelCost();
        
        InterfaceStaticMethod.printSpliter();
        
        testParamllelThreadName();
        testForkJoinPool();
    }

    private static void testForkJoinPool() {
        System.out.println("forkJoinPool.getParallelism:");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }
}
