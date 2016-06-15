/**
 * The MIT License
 * Copyright (c) 2014 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tutorial.java8.stream.fluentapipattern.app;

import com.tutorial.java8.stream.fluentapipattern.fluentiterable.FluentIterable;
import com.tutorial.java8.stream.fluentapipattern.fluentiterable.lazy.LazyFluentIterable;
import com.tutorial.java8.stream.fluentapipattern.fluentiterable.simple.SimpleFluentIterable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.String.valueOf;

/**
 * The Fluent Interface pattern is useful when you want to provide an easy readable, flowing API.
 * Those interfaces tend to mimic domain specific languages, so they can nearly be read as human
 * languages.
 * <p>
 * In this example two implementations of a {@link FluentIterable} interface are given. The
 * {@link SimpleFluentIterable} evaluates eagerly and would be too costly for real world
 * applications. The {@link LazyFluentIterable} is evaluated on termination. Their usage is
 * demonstrated with a simple number list that is filtered, transformed and collected. The result is
 * printed afterwards.
 * 
 */
public class App {

  /**
   * Program entry point
   */
  public static void main(String[] args) {

    List<Integer> integerList = new ArrayList<>();
    integerList.addAll(Arrays.asList(1, -61, 14, -22, 18, -87, 6, 64, -82, 26, -98, 97, 45, 23, 2,
        -68, 45));

    prettyPrint("The initial list contains: ", integerList);

    List<Integer> firstFiveNegatives =
        SimpleFluentIterable.fromCopyOf(integerList).filter(negatives()).first(3).asList();
    prettyPrint("The first three negative values are: ", firstFiveNegatives);


    List<Integer> lastTwoPositives =
        SimpleFluentIterable.fromCopyOf(integerList).filter(positives()).last(2).asList();
    prettyPrint("The last two positive values are: ", lastTwoPositives);

    SimpleFluentIterable
        .fromCopyOf(integerList)
        .filter(number -> number % 2 == 0)
        .first()
        .ifPresent(
            evenNumber -> System.out.println(String.format("The first even number is: %d",
                evenNumber)));


    List<String> transformedList =
        SimpleFluentIterable.fromCopyOf(integerList).filter(number -> number < 0).map(transformToString())
            .asList();
    prettyPrint("A string-mapped list of negative numbers contains: ", transformedList);


    List<String> lastTwoOfFirstFourStringMapped =
        LazyFluentIterable.from(integerList).filter(positives()).first(4).last(2)
            .map(number -> "String[" + valueOf(number) + "]").asList();
    prettyPrint(
        "The lazy list contains the last two of the first four positive numbers mapped to Strings: ",
        lastTwoOfFirstFourStringMapped);

    LazyFluentIterable
        .from(integerList)
        .filter(negatives())
        .first(2)
        .last()
        .ifPresent(
            lastOfFirstTwo -> System.out.println(String.format(
                "The last of the first two negatives is: %d", lastOfFirstTwo)));
  }

  private static Function<Integer, String> transformToString() {
    return integer -> "String[" + valueOf(integer) + "]";
  }

  private static Predicate<? super Integer> negatives() {
    return integer -> integer < 0;
  }

  private static Predicate<? super Integer> positives() {
    return integer -> integer > 0;
  }

  private static <E> void prettyPrint(String prefix, Iterable<E> iterable) {
    prettyPrint(", ", prefix, iterable);
  }

  private static <E> void prettyPrint(String delimiter, String prefix,
                                         Iterable<E> iterable) {
    StringJoiner joiner = new StringJoiner(delimiter, prefix, ".");
    Iterator<E> iterator = iterable.iterator();
    while (iterator.hasNext()) {
      joiner.add(iterator.next().toString());
    }

    System.out.println(joiner);
  }
}