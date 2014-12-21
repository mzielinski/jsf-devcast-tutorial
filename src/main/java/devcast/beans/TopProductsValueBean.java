package devcast.beans;

import devcast.entities.Product;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.List;

import static devcast.dao.GeneralDao.DAO_INSTANCE;
import static java.util.stream.Collectors.toList;

/**
 * @author mzielinski on 16.12.14.
 */
@ManagedBean
@ApplicationScoped
public class TopProductsValueBean implements Serializable {

    private Product selectedProduct;
    private List<SelectItem> topProducts;

    public TopProductsValueBean() {
        topProducts = DAO_INSTANCE.findTopProducts().stream()
            .map(product -> new SelectItem(product, product.getName()))
            .collect(toList());
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<SelectItem> getTopProducts() {
        return topProducts;
    }

}