package com.tutorial.java8.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by xiecong on 16/6/17.
 */
public class CompletableFutureExample {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }
    public static void main(String[] args) throws Exception {
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureExample::getMoreData);
//        Future<Integer> f = future.whenComplete((v, e) -> {
//            System.out.println(v);
//            System.out.println(e);
//        });
//        System.out.println("hello world");
//        System.out.println(f.get());
//        System.in.read();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 100;
        });
        CompletableFuture<String> f =  future.thenApplyAsync(i -> {
            System.out.println("i*10");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i * 10;}).thenApply(i -> {
            System.out.println("toString");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i.toString();});
        System.out.println("hello world");
        System.out.println(f.get()); //"1000"
    }
}
