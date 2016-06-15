package com.tutorial.java8.lambda;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by xiecong on 16/6/14.
 *
 * Method reference is abbreviation of lambda expression
 * abbrev table
 *
 * <p>
 * Method reference	 	Equivalent lambda expression
 * -------------------------------------------------
 * String::valueOf		x -&gt; String.valueOf(x)
 * Object::toString		(x) -&gt; x.toString()
 * x::toString		    () -&gt; x.toString()
 * ArrayList::new		() -&gt; new ArrayList&lt;&gt;()
 * </p>
 */
public class LambdaExpressionMethodReference {

    @FunctionalInterface
    interface  StaticMethodReference{
        long convertStr2Long(String s);
    }

    @FunctionalInterface
    interface  ObjectMethodReference{
        String convertLong2Str(Long l);
    }

    @FunctionalInterface
    interface  NonStaticMethodReference{
        long incr();
    }

    @FunctionalInterface
    interface ConstructorMethodReference<T>{
        ArrayList<T> get();
    }


    public static void main(String[] args) {

        StaticMethodReference staticMethodReference = Long::valueOf;//equal: (String x) -> Long.valueOf(x)
        System.out.println("staticMethodReference: " + staticMethodReference.convertStr2Long("123"));

        ObjectMethodReference objectMethodReference = Object::toString;//equal: (x) -> x.toString()
        System.out.println("objectMethodReference: " + objectMethodReference.convertLong2Str(321L));

        AtomicLong l = new AtomicLong(0);
        NonStaticMethodReference nonStaticMethodReference = l::incrementAndGet; //equal: () -> l.incrementAndGet()
        System.out.println("nonStaticMethodReference: " + nonStaticMethodReference.incr());

        ConstructorMethodReference<Integer> constructorMethodReference = ArrayList<Integer>::new; // equal: () -> new ArrayList<>();
        ArrayList<Integer> arrayList = constructorMethodReference.get();
        System.out.println("constructorMethodReference: " + arrayList);


    }
}
