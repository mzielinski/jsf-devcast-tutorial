package devcast.utils;

/**
 * @author mzielinski on 21.12.14.
 */
public class Preconditions {

    public static void greaterThenZero(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(String.format("Value [%d] cannot be lower then zero", value));
        }
    }

}
