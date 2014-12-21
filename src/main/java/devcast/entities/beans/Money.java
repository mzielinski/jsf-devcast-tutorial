package devcast.entities.beans;

import java.math.BigDecimal;

/**
 * @author mzielinski on 15.12.14.
 */
public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money(int value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }



}
