package devcast.controller;

import devcast.beans.CardValueBean;
import devcast.entities.Element;
import devcast.entities.Order;
import devcast.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
public class CardController {

    @ManagedProperty("#{cardValueBean}")
    private CardValueBean cardValueBean;

    public String finishOrder() {
        DAO_INSTANCE.addOrder(cardValueBean.getOrder());
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

    public String addToCard() {
        notNull(cardValueBean.getSelectedProduct(), "selected product");
        notNull(cardValueBean.getOrder(), "order");

        final Product selectedProduct = cardValueBean.getSelectedProduct();
        final Optional<Element> selectedElement = cardValueBean.getOrder().getElements().stream()
                .filter(element -> Objects.equals(element.getProduct(), selectedProduct))
                .findFirst();
        if (selectedElement.isPresent()) {
            selectedElement.get().increaseCount();
        } else {
            cardValueBean.getOrder().getElements().add(anElement()
                    .withAmount(selectedProduct.getAmount())
                    .withProduct(selectedProduct)
                    .withOrder(cardValueBean.getOrder())
                    .withCount(1)
                    .build());
        }
        return "card.xhtml";
    }

    public CardValueBean getCardValueBean() {
        return cardValueBean;
    }

    public void setCardValueBean(CardValueBean cardValueBean) {
        this.cardValueBean = cardValueBean;
    }

}
