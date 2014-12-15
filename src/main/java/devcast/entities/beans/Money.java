package devcast.entities.beans;

import java.math.BigDecimal;

/**
 * @author mzielinski on 15.12.14.
 */
public class Money {

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
