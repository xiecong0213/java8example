package com.tutorial.java8.lambda;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by xiecong on 16/6/14.
 *
 * <p>
 *     Lambda Expression Constraints
 *     <ul>
 *         <li>can't change non-final variable in lambda expression</li>
 *         <li>can't throw checked exception in lambda expression</li>
 *         <li>can't break,early return</li>
 *     </ul>
 * </p>
 */
public class LambdaExpressionConstraints {


    final String secret = "foo";
    boolean canNotEarlyReturn(Iterable<String> values) {
        values.forEach(s -> {
            if (secret.equals(s)) {
                 // want to end the loop and return true, but can't
            }
        });
        return true;
    }

    //can't throw checked exception,besides you can convert unchecked exception to RuntimeException
    void canNotThrowUncheckException(Iterable<String> values, Appendable out)
            throws IOException { // doesn't help with the error
        values.forEach(s -> {
//            out.append(s); // error: can't throw IOException here
            // Consumer.accept(T) doesn't allow it
        });
    }

    void canNotChangeNonFinalVar(){
        int x = 1;
        //comment line can't pass compile
//        IntStream.range(0,10).forEach(i -> x++); //error: despite x is non-final
    }

}
