package com.tutorial.java8.lambda;

import java.util.List;

/**
 * Created by xiecong on 16/6/14.
 *
 * <p>
 * interface can implement a default method.
 * in java 8,a large number of default methods have been added to core JDK interfaces.
 * @see List#sort(java.util.Comparator)
 * @see java.util.List or other subclass of java.util.Collection
 * <p>
 *
 * <p>
 * Warning
 * An interface cannot provide a default implementation for any of the methods of the Object class.
 * In particular, this means one cannot provide a default implementation for equals, hashCode, or toString from within an interface.
 * </p>
 */

public interface InterfaceDefaultMethod {

    default String concat(String a, String b) {
        return a + b;
    }



    static void main(String[] args) {
        System.out.println(new InterfaceDefaultMethod() {
        }.concat("first ", "second"));
    }
}
