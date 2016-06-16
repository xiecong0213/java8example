package com.tutorial.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xiecong on 16/6/15.
 * warning:
 * stream depend on statueful variable may cause different presentation.
 */
public class StreamWithStateful {

    final static StateHoldClass stateHoldClass = new StateHoldClass();

    static class StateHoldClass {
        private boolean curState;
    }

    public static void main(String[] args) {
        Stream<Integer> sl = IntStream.range(0,10).boxed().parallel().map(e -> {
            if (stateHoldClass.curState)
                return 0;
            else {
                stateHoldClass.curState = true;
                return e;
            }
        });
        sl.forEach(System.out::println);
    }
}
