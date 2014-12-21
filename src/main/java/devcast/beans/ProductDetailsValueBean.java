package devcast.beans;

import devcast.dao.GeneralDao;
import devcast.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * @author mzielinski on 21.12.14.
 */
@ManagedBean
@RequestScoped
public class ProductDetailsValueBean {

    private Product selectedProduct;

    @ManagedProperty("#{param.id}")
    private int productId;

    @ManagedProperty("#{topProductsValueBean}")
    private TopProductsValueBean topProductsValueBean;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId > 0) {
            this.selectedProduct = GeneralDao.DAO_INSTANCE.findProductById(productId).get();
            this.productId = productId;
        }
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public TopProductsValueBean getTopProductsValueBean() {
        return topProductsValueBean;
    }

    public void setTopProductsValueBean(TopProductsValueBean topProductsValueBean) {
        this.topProductsValueBean = topProductsValueBean;
        final Product selectedProduct = topProductsValueBean.getSelectedProduct();
        if (selectedProduct != null) {
            this.selectedProduct = selectedProduct;
        }
    }

}
