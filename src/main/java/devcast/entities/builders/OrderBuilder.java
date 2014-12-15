package devcast.entities.builders;

import devcast.entities.Element;
import devcast.entities.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mzielinski on 15.12.14.
 */
public class OrderBuilder {

    private long id;
    private Date createDate;
    private List<Element> elements = new ArrayList<>();

    private OrderBuilder() {
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }

    public OrderBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public OrderBuilder withElement(Element element) {
        this.elements.add(element);
        return this;
    }

    public OrderBuilder withElements(List<Element> elements) {
        this.elements.addAll(elements);
        return this;
    }

    public OrderBuilder but() {
        return anOrder().withId(id).withCreateDate(createDate).withElements(elements);
    }

    public Order build() {
        Order order = new Order();
        order.setId(id);
        order.setCreateDate(createDate);
        order.setElements(elements);
        return order;
    }

}
