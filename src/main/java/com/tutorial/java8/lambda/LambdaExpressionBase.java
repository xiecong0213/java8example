package com.tutorial.java8.lambda;

/**
 * Created by xiecong on 16/6/14.
 */
public class LambdaExpressionBase {

    @FunctionalInterface
    interface IncrNumber<T> {
        /**
         * method only one param
         */
        T incr(T x);
    }

    @FunctionalInterface
    interface PrintHello {
        /**
         * method only one param
         */
        void print();
    }

    public static void main(String[] args) {

        //full lambda expression: comma-separated list of inputs params with specific types, a block with a return on the right
        FunctionalInteraceExample<Integer> fullExpression = (Integer l, Integer r) -> {
            return l + r;
        };
        System.out.println(fullExpression.add(1, 2));// expect output=3

        //abbrev lambda expression(more than one params): omit param type,method block,return
        FunctionalInteraceExample<Integer> abbrevExpreesionWithMultiParams = (l, r) -> l + r;
        System.out.println(abbrevExpreesionWithMultiParams.add(1, 2));//expect output=3


        //abbrev lambda expression(only one param): omit param type,method block,return, addtional omit param brackets
        IncrNumber<Integer> abbrevExpreesionWithOneParam = x -> x + 1;
        System.out.println(abbrevExpreesionWithOneParam.incr(1));//expect output=2

        //abbrev lambda expression(no param): omit param type,param brackets,method block,return
        PrintHello abbrevExpreesionWithNoParam = () -> System.out.println("hello lambda");
        abbrevExpreesionWithNoParam.print();//expect output=hello lambda

    }
}
