package devcast.entities.builders;

import devcast.entities.Element;
import devcast.entities.Order;
import devcast.entities.Product;
import devcast.entities.beans.Money;

/**
 * @author mzielinski on 15.12.14.
 */
public class ElementBuilder {

    private int count;
    private Money amount;
    private Product product;
    private Order order;

    private ElementBuilder() {
    }

    public static ElementBuilder anElement() {
        return new ElementBuilder();
    }

    public ElementBuilder withCount(int count) {
        this.count = count;
        return this;
    }

    public ElementBuilder withAmount(Money amount) {
        this.amount = amount;
        return this;
    }

    public ElementBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public ElementBuilder withOrder(Order order) {
        this.order = order;
        return this;
    }

    public ElementBuilder but() {
        return anElement().withCount(count).withAmount(amount).withProduct(product).withOrder(order);
    }

    public Element build() {
        Element element = new Element();
        element.setCount(count);
        element.setAmount(amount);
        element.setProduct(product);
        element.setOrder(order);
        return element;
    }
}
