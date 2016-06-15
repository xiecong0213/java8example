package com.tutorial.java8.stream;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int age;
    private double store;
    private List<String> books = new ArrayList<>();

    public Student(String name, int age, double store) {
        this.name = name;
        this.age = age;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getStore() {
        return store;
    }

    public List<String> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", store=" + store +
                '}';
    }
}