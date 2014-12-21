package devcast.entities;

import devcast.entities.beans.Money;

import java.math.BigDecimal;

/**
 * @author mzielinski on 15.12.14.
 */
public class Element {

    private int count;
    private Money amount;
    private Product product;
    private Order order;

    public void increaseCount() {
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (order != null ? !order.equals(element.order) : element.order != null) return false;
        if (product != null ? !product.equals(element.product) : element.product != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Money getTotal() {
        final BigDecimal total = getAmount().getValue().multiply(new BigDecimal(count));
        return new Money(total);
    }
}
