package com.tutorial.java8.lambda;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Created by xiecong on 16/6/14.
 * <p>
 * interface static method can help you reduce the number of Utils static method
 * @see Comparator#naturalOrder()
 * class below show new time api in java 8,more info about time api @see <a href="http://www.importnew.com/14140.html">new time api</a>
 * </p>
 */
public interface InterfaceStaticMethod {

    static LocalDate getOneWeekAfter() {
        return LocalDate.now().plusWeeks(1);
    }

    static void printSpliter(){
        System.out.println();
        System.out.println("------------------------------------------------------------");
    }

    static void main(String[] args) {
        System.out.println(getOneWeekAfter());
    }
}
