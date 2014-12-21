package devcast.beans;

import devcast.entities.Element;
import devcast.entities.Order;
import devcast.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @author mzielinski on 16.12.14.
 */
@ManagedBean
@SessionScoped
public class CardValueBean implements Serializable {

    private Order order = new Order();
    private Element selectedElement;
    private Product selectedProduct;

    public void resetOrder() {
        order = new Order();
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Element getSelectedElement() {
        return selectedElement;
    }

    public void setSelectedElement(Element selectedElement) {
        this.selectedElement = selectedElement;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}