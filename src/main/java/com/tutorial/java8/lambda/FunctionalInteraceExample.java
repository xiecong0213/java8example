package com.tutorial.java8.lambda;

/**
 * Created by xiecong on 16/6/14.
 *
 * <p>
 *     An interface is a functional interface if it defines exactly one abstract method.
 *     For instance, java.lang.Runnable is a functional interface because it only defines one abstract
 * </p>
 *
 * <p>
 *     simple example of functional interface for add two numbers
 *     Desipte explict @FunctionalInterface is unnecessary,code it when you create a functional interface can help you find error in compile time.
 *     just like @override,if you don't define exactly one abstrct method,compiler will hint you with error mark.
 * </p>
 *
 */
@FunctionalInterface
public interface FunctionalInteraceExample<T extends Number> {

    /**
     * exactly one abtract method.
     * interface method is abstract,so you can leave out the abstract keyword
     * if you comment add method,compile will hint you no target method found
     */
    T add(T left,T right);

    default Number minus(T left,T right)
    {
        return  left.intValue() - right.intValue();
    }

    static void main(String[] args) {
        int left = 1;
        int right = 2;

        //you will learn implement FunctionalInterface with lambda expression in next step
        FunctionalInteraceExample<Integer> impl = (l,r) -> l + r;
        System.out.println(impl.add(left,right));//expect output=3
    }
}
