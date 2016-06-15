package com.tutorial.java8.lambda;

import java.util.stream.IntStream;

/**
 * Created by xiecong on 16/6/14.
 * <p>
 *     Lambdas are said to be "capturing" if they access a non-static variable or object that was defined outside of the lambda body.
 *     this may cause performance problem,when execute the capturing lambda expression,a new instance of an anonymous class will be created.
 *     output of the object hashcode proved capturing may cause performance problem
 * </p>
 */
public class LambdaExpressionCapturing {

    private static final int LOOP = 1000000;
    @FunctionalInterface
    interface CaptureConsumer<T>{
        void accept(T t);
    }


    private static void printCaptureConsumerObject(CaptureConsumer<Integer> captureConsumer)
    {
        System.out.println(captureConsumer);
        captureConsumer.accept(1);
    }

    public static void main(String[] args) {
        int x = 0;
        //capture x in captureConsumer.accept() method
        IntStream.range(0,3).forEach(i -> printCaptureConsumerObject(t -> {int z = x+1;}));

        //if we change x value, compile failed,x must be final or effectively final
//        x= 1;

        InterfaceStaticMethod.printSpliter();

        //non-capture x in captureConsumer.accept() method
        IntStream.range(0,3).forEach(i -> printCaptureConsumerObject(t -> {int z = t+1;}));

    }
}
