package com.tutorial.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

/**
 * Created by xiecong on 16/6/14.
 * <p>
 * a lot of commonly useful FunctionalInterface are added to JDK8.
 * @see java.util.stream.Stream#map(java.util.function.Function)
 * <ul>
 *     <li>Function<T,R> T as input,R as output,use for map one object to another object </li>
 *     <li>Predicate<T> T as input,return Boolean,use for filter and other scenario which need judge true or false</li>
 *     <li>Consumer<T> - T as input, perform some action and don't return anything</li>
 *     <li>Supplier<T> - with nothing as input, return a T</li>
 *     <li>BinaryOperator<T> - take two T's as input, return one T as output, useful for "reduce" operations</li>
 * </ul>
 * </p>
 */
public class FunctionalInterfaceSupportByJDK8 {

    private static void functionExample(List<Integer> list, Function<Integer,Long> sqrFunction){
        list.stream().map(sqrFunction).forEach(x -> System.out.print(x + " "));
    }

    private static void predicateExample(List<Integer> list, Predicate<Integer> predicate){
        list.stream().filter(predicate).forEach(x -> System.out.print(x + " "));
    }

    private static void consumerExample(List<Integer> list, Consumer<Integer> integerConsumer){
        list.stream().forEach(integerConsumer);
    }

    private static ArrayList<Integer> supplierExample(Supplier<ArrayList<Integer>> supplier)
    {
        return supplier.get();
    }

    private static Optional<Integer> binaryOperatorExample(List<Integer> list, BinaryOperator<Integer> binaryOperator){
        return list.stream().reduce(binaryOperator);
    }

    public static void main(String[] args) {

        //function lambda expression
        functionExample(Arrays.asList(1,2,3), x -> Long.valueOf(x) * x);

        InterfaceStaticMethod.printSpliter();

        //predicate lambda expression,filter even
        predicateExample(Arrays.asList(1,2,3,4,5),x -> x % 2 == 0);

        InterfaceStaticMethod.printSpliter();

        //consumer lambda expression, store list element in other list
        List<Integer> outputList = new ArrayList<>();
        consumerExample(Arrays.asList(1,2,3),x -> outputList.add(x));
        outputList.forEach(x -> System.out.print(x + " "));

        InterfaceStaticMethod.printSpliter();

        //supplier lambda expression,create ArrayList
        ArrayList<Integer> returnList = supplierExample(ArrayList<Integer>::new);
        System.out.print(returnList);

        InterfaceStaticMethod.printSpliter();

        //BinaryOperator
        Optional<Integer> optional = binaryOperatorExample(Arrays.asList(1,2,3),(x,y) -> x + y);
        optional.ifPresent(System.out::print);
    }
}
