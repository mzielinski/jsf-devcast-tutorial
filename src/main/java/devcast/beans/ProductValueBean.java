package devcast.beans;

import devcast.entities.Category;
import devcast.entities.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

import static devcast.dao.GeneralDao.DAO_INSTANCE;
import static devcast.dao.GeneralDao.PAGE_SIZE;

/**
 * @author mzielinski on 16.12.14.
 */
@ManagedBean
@SessionScoped
public class ProductValueBean implements Serializable {

    private ListDataModel<Product> productModel = new ListDataModel<>();
    private Category category;
    private int page = 0;

    public ProductValueBean() {
        retrieveProductList();
    }

    public boolean isPrevious() {
        return page > 0;
    }

    public boolean isNext() {
        final Double maxPage = Math.ceil(DAO_INSTANCE.countProducts(category)) / PAGE_SIZE;
        return page < maxPage.intValue();
    }

    public String gotoNextPage() {
        page++;
        retrieveProductList();
        return "";
    }

    public String gotoPreviousPage() {
        page--;
        retrieveProductList();
        return "";
    }

    private void retrieveProductList() {
        productModel.setWrappedData(DAO_INSTANCE.findProductsByCategory(category, page * PAGE_SIZE, PAGE_SIZE));
    }

    public ListDataModel<Product> getProductModel() {
        return productModel;
    }

    public void setProductModel(ListDataModel<Product> productModel) {
        this.productModel = productModel;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.page = 0;
        retrieveProductList();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}