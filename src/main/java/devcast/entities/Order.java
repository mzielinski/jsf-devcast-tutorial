package devcast.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mzielinski on 15.12.14.
 */
public class Order {

    private long id;
    private Date createDate;
    private User user;
    private List<Element> elements;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Element> getElements() {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}
