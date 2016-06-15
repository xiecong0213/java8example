package com.tutorial.java8.lambda.factorypattern;

import java.util.Random;

/**
 * Created by xiecong on 16/6/4.
 */
public enum EnumCarType {
    MICROCAR,COMPACT_CAR,MID_SIZE_CAR,FULL_SIZE_CAR;

    public static EnumCarType random()
    {
        Random r = new Random();
        return EnumCarType.values()[r.nextInt(EnumCarType.values().length)];
    }
}
