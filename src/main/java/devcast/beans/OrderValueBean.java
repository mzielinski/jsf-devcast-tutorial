package devcast.beans;

import devcast.entities.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

import static devcast.dao.GeneralDao.DAO_INSTANCE;

/**
 * @author mzielinski on 16.12.14.
 */
@ManagedBean
@RequestScoped
public class OrderValueBean implements Serializable {

    private ListDataModel<Category> categoryModel = new ListDataModel<>();

    public OrderValueBean() {
        categoryModel.setWrappedData(DAO_INSTANCE.findAllCategories());
    }

    public ListDataModel<Category> getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(ListDataModel<Category> categoryModel) {
        this.categoryModel = categoryModel;
    }

}