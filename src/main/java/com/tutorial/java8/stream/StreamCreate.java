package com.tutorial.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by xiecong on 16/6/15.
 * multi create method:
 * <ul>
 *  <li>call collection.stream() or parallelStream method get a stream</li>
 *  <li>Arrays.stream(Object[])</li>
 *  <li>Stream static method, Stream.of, IntStream.range,Stream.generate,Stream.iterate</li>
 *  <li>get line stream from file use. BufferedReader.lines()</li>
 *  <li>Files operate method,list,find,walk</li>
 *  <li>Random.ints()</li>
 *  <li>other static method,BitSet.stream(),Pattern.splitAsStream(java.lang.CharSequence)</li>
 * </ul>
 */
public class StreamCreate {

    public static void main(String[] args) throws IOException {

        createStreamFromCollection();

        createStreamFromArray();

        createStreamFromStreamStaticMethod();

        createStreamFromStreamSupportStaticMethod();

        createStreamFromFilesStaticMethod();

        createStreamFromRandom();

        createStreamFromPattern();

        createStreamFromStreamBuilder();
    }

    private static void createStreamFromStreamStaticMethod() {
        System.out.println("create from stream.iterate:");
        int[] nums = IntStream.iterate(1, n -> n * 2)
                .limit(11)
                .toArray();
        System.out.println(Arrays.toString(nums));
    }

    private static void createStreamFromCollection(){
        List<String> stringList = Arrays.asList("a","b","c");
        System.out.println("create from list:");
        stringList.forEach((x) -> System.out.println(x));

        System.out.println("create from Set:");
        Set<String> stringSet = new HashSet<>(Arrays.asList("d","e","f"));
        stringSet.forEach(System.out::println);

        Map<String, String> map = new HashMap<String, String>() {{
            put("key1","value1");
            put("key2","value2");
        }};

        System.out.println("create from Map.EntrySet:");
        map.entrySet().stream().forEach(entry-> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    private static void createStreamFromArray() {
        System.out.println("create from array:");
        Arrays.stream(new int[]{1,2,3}).forEach(System.out::println);
    }

    private static void createStreamFromPattern() {
        String string = Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"));
        System.out.println("create stream from pattern split: " + string);
    }

    private static void createStreamFromRandom() {
        Random random = new Random();
        String join = random.ints(3).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        Stream.generate(() -> random.nextInt(10)).limit(3).forEach(System.out::println);
        System.out.println("craete stream from random:" + join);
    }

    private static void createStreamFromFilesStaticMethod() throws IOException {
        System.out.println();
        Stream<Path> listStream = Files.list(Paths.get(""));
        String joined = listStream
                .map(String::valueOf)
                .filter(path -> !path.startsWith("."))
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println("create stream from Files.list : " + joined);

        Path start = Paths.get("");
        int maxDepth = 8;
        try (Stream<Path> findStream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".java"))) {
            String joined1 = findStream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("create stream from Files.find: " + joined1);
        }
    }

    private static void createStreamFromStreamSupportStaticMethod()  {
        System.out.println("create from stream static method:");
        Stream.of("a","b","c").forEach(System.out::print);

        System.out.println();

        Stream.generate(()->"a").limit(3).forEach(System.out::print);

        System.out.println();
        IntStream.range(0,3).forEach(System.out::print);

        System.out.println();
        Iterator<String> sourceIterator = Arrays.asList("b", "b", "b").iterator();
        Iterable<String> iterable = () -> sourceIterator;
        StreamSupport.stream(iterable.spliterator(), false).forEach(System.out::print);
    }

    private static void createStreamFromStreamBuilder(){
        IntStream
                .builder()
                .add(1)
                .add(3)
                .add(5)
                .add(7)
                .add(11)
                .build()
                .average()
                .ifPresent(System.out::println);
    }

}
