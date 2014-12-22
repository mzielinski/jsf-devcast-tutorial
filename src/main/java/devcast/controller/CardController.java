package devcast.controller;

import devcast.beans.CardValueBean;
import devcast.entities.Element;
import devcast.entities.Order;
import devcast.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static devcast.dao.GeneralDao.DAO_INSTANCE;
import static devcast.entities.builders.ElementBuilder.anElement;
import static devcast.utils.Preconditions.notNull;
import static java.util.stream.Collectors.toList;

/**
 * @author mzielinski on 21.12.14.
 */
@ManagedBean
@RequestScoped
public class CardController implements Serializable {

    @ManagedProperty("#{cardValueBean}")
    private CardValueBean cardValueBean;

    public String finishOrder() {
        DAO_INSTANCE.processOrder(cardValueBean.getOrder());
        cardValueBean.resetOrder();
        return "success";
    }

    public String deleteElement() {
        final Order order = cardValueBean.getOrder();
        final List<Element> elements = order.getElements().stream()
                .filter(element -> !Objects.equals(cardValueBean.getSelectedElement(), element))
                .collect(toList());
        order.setElements(elements);
        return "";
    }

    public void actionListener(ActionEvent event) {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final String productId = context.getRequestParameterMap().get("productId");
        final Optional<Product> selectedProduct = DAO_INSTANCE.findProductById(Integer.parseInt(productId));
        cardValueBean.setSelectedProduct(selectedProduct.get());
    }

    public String addToCard() {
        notNull(cardValueBean.getSelectedProduct(), "selected product");
        notNull(cardValueBean.getOrder(), "order");

        final Product selectedProduct = cardValueBean.getSelectedProduct();
        final List<Element> allElements = cardValueBean.getOrder().getElements();
        final Optional<Element> productFromOrder = findElementForSelectedProduct(selectedProduct, allElements);
        if (productFromOrder.isPresent()) {
            productFromOrder.get().increaseCount();
        } else {
            final Element newElement = buildNewElement(selectedProduct, cardValueBean.getOrder());
            allElements.add(newElement);
        }
        return "card.xhtml";
    }

    private Optional<Element> findElementForSelectedProduct(Product selectedProduct, List<Element> allElements) {
        return allElements.stream()
                .filter(element -> Objects.equals(element.getProduct(), selectedProduct))
                .findFirst();
    }

    private Element buildNewElement(Product selectedProduct, Order order) {
        return anElement()
                .withAmount(selectedProduct.getAmount())
                .withProduct(selectedProduct)
                .withOrder(order)
                .withCount(1)
                .build();
    }

    public CardValueBean getCardValueBean() {
        return cardValueBean;
    }

    public void setCardValueBean(CardValueBean cardValueBean) {
        this.cardValueBean = cardValueBean;
    }


}
