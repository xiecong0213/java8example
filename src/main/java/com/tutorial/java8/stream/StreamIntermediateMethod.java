package com.tutorial.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by xiecong on 16/6/15.
 * <p>
 * <p>
 * Intermediate - An intermediate operation keeps the stream open and allows further operations to follow.
 * The return type of these methods is Stream; they return the current stream to allow chaining of more operations.
 * <ul>
 * <li>filter:Returns a stream consisting of the elements of this stream that match the given predicate</li>
 * <li>map:Returns a stream consisting of the results of applying the given function to the elements of this stream</li>
 * <li>flatMap:Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.</li>
 * <li>peek:Perform some action on each element as it is encountered. Primarily useful for debugging</li>
 * <li>distinct: Exclude all duplicate elements according to their .equals behavior. This is a stateful operation.</li>
 * <li>sorted: Ensure that stream elements in subsequent operations are encountered according to the order imposed by a Comparator. This is a stateful operation.</li>
 * <li>limit:Ensure that subsequent operations only see up to a maximum number of elements. This is a stateful, short-circuiting operation.</li>
 * <li>skip:Ensure that subsequent operations do not see the first n elements. This is a stateful operation.</li>
 * </ul>
 * </p>
 */
public class StreamIntermediateMethod {

    public static void main(String[] args) throws IOException {
        testFilter();
        testMap();
        testFlatMap();
        testPeek();
        testDistinct();
        testSorted();
        testLimit();
        testSkip();
        testMix();
    }

    private static void testMix() {
        Map<String, List<Student>> school = new HashMap();
        school.put("mathClass", Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0)));
        school.put("SportClass", Arrays.asList(new Student("James", 24, 98.0), new Student("Messi", 28, 100.0),new Student("HanMeiMei",24,56.0)));
        school.put("filterdClass", Arrays.asList(new Student("James", 24, 98.0), new Student("Messi", 28, 100.0),new Student("HanMeiMei",24,56.0)));

        //order by student score desc
        Map<String, List<Student>> ordered = school.entrySet().stream().filter(e -> e.getKey() != "filterdClass")
                .collect(Collectors
                        .toMap(Map.Entry::getKey, e -> e.getValue().stream().filter(v -> v.getStore() > 60).sorted((x, y) -> (int) (y.getStore() - x.getStore()))
                                .collect(Collectors.toList())));
        System.out.println(ordered);
    }

    private static void testSkip() {
        System.out.println("skip:");
        IntStream.range(0, 10).skip(5).forEach(System.out::print);
        System.out.println();
    }

    private static void testLimit() {
        System.out.println("limit:");
        IntStream.range(0, 10).limit(3).forEach(System.out::print);
        System.out.println();
    }

    private static void testSorted() {
        System.out.println("sort:");
        new Random().ints().limit(10).sorted().forEach(System.out::println);
    }

    private static void testDistinct() {
        System.out.println("distinct:");
        Arrays.asList("Peter", "Tom", "Jack", "Tom").stream().distinct().forEach(System.out::print);
        System.out.println();
    }

    private static void testPeek() {
        System.out.println("peek:");

        Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY").stream().peek(x -> System.out.print(x.toLowerCase() + "|")).forEach(System.out::println);
    }

    private static void testFlatMap() throws IOException {
        System.out.println("flatMap:");

        Files.readAllLines(Paths.get("src/main/resources/test.txt")).stream()
                .flatMap(s -> Pattern.compile(" ").splitAsStream(s)).forEach(x -> System.out.print(x + "|"));

        System.out.println();
    }

    private static void testMap() {
        System.out.println("map:");
        LongStream.range(0, 10).map(x -> x * 2).forEach(System.out::print);
    }

    private static void testFilter() {
        System.out.println("filter:");
        IntStream.range(0, 10).filter(x -> x > 4).forEach(System.out::print);
        System.out.println();
    }
}
