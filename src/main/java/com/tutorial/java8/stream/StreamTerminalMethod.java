package com.tutorial.java8.stream;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xiecong on 16/6/15.
 * <p>
 * <ul>
 * <li>forEach: Perform some action for each element in the stream.</li>
 * <li>toArray: Dump the elements in the stream to an array.</li>
 * <li>reduce: Combine the stream elements into one using a BinaryOperator.</li>
 * <li>collect: Dump the elements in the stream into some container, such as a Collection or Map.</li>
 * <li>min: Find the minimum element of the stream according to a Comparator.</li>
 * <li>max: Find the maximum element of the stream according to a Comparator.</li>
 * <li>count: Find the number of elements in the stream.</li>
 * <li>anyMatch: Find out whether at least one of the elements in the stream matches a Predicate. This is a short-circuiting operation.</li>
 * <li>allMatch: Find out whether every element in the stream matches a Predicate. This is a short-circuiting operation.</li>
 * <li>noneMatch: Find out whether zero elements in the stream match a Predicate. This is a short-circuiting operation.</li>
 * <li>findFirst: Find the first element in the stream. This is a short-circuiting operation.</li>
 * <li>findAny: Find any element in the stream, which may be cheaper than findFirst for some streams. This is a short-circuiting operation.</li>
 * </ul>
 */
public class StreamTerminalMethod {
    public static void main(String[] args) {
        testToArray();
        testReduce();
        testCollect();
        testMinAndMax();
        testCount();
        testMatch();
        testFind();
        testMix();
    }

    private static void testMix() {
        System.out.println("mix:");
        IntStream.range(20, 22)
                .mapToObj(num -> new Student("LaoWang" + num, num, num * 2))
                .peek(f -> IntStream.range(20, 22)
                        .mapToObj(num -> new String(f.getName() + "' book" + num))
                        .forEach(f.getBooks()::add))
                .flatMap(f -> f.getBooks().stream())
                .forEach(b -> System.out.println(b));
    }

    private static void testFind() {
        System.out.println("find:");
        List<String> stringList = Arrays.asList("Tom", "Jack", "LiLei", "Trump", "Tom");

        stringList.stream().findAny().ifPresent(System.out::println);

        stringList.stream().findFirst().ifPresent(System.out::println);
    }

    private static void testMatch() {
        System.out.println("match:");
        List<String> stringList = Arrays.asList("Tom", "Jack", "LiLei", "Trump");
        System.out.println(stringList.stream().anyMatch(x -> "Tom".equals(x)));//true
        System.out.println(stringList.stream().allMatch(x -> "Tom".equals(x)));//false
        System.out.println(stringList.stream().noneMatch(x -> "Tom".equals(x)));//false
    }

    private static void testCount() {
        System.out.println("count:");
        System.out.println(IntStream.range(0, 10).count());
    }

    private static void testMinAndMax() {
        System.out.println("min:");
        IntStream.range(0, 10).min().ifPresent(System.out::println);
        System.out.println("max:");
        IntStream.range(0, 10).max().ifPresent(System.out::println);
        ;
    }

    private static void testCollect() {
        System.out.println("collect:");
        List<Integer> collectionList = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(collectionList);

        Set<Integer> collectionSet = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        System.out.println(collectionSet);

        Map<Integer, Integer> collectionMap = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toMap(x -> x, x -> 2 * x));
        System.out.println(collectionMap);

        Collector<Student, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.getName().toUpperCase() + "");
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0))
                .parallelStream()
                .collect(personNameCollector);

        System.out.println(names);  // MAX | PETER | PAMELA | DAVID
    }

    private static void testReduce() {
        System.out.println("reduce:");
        IntStream.range(0, 10).reduce((x, y) -> x + y).ifPresent(System.out::println);
    }

    private static void testToArray() {
        System.out.println("toArray:");
        int[] nums = IntStream.iterate(1, n -> n * 2)
                .limit(11)
                .toArray();
        System.out.println(Arrays.toString(nums));
    }
}