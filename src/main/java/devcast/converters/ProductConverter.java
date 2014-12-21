package devcast.converters;

import devcast.entities.Product;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import static devcast.dao.GeneralDao.DAO_INSTANCE;

/**
 * @author mzielinski on 21.12.14.
 */
@FacesConverter(value="productConverter")
public class ProductConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        final int productId = Integer.valueOf(value);
        return DAO_INSTANCE.findProductById(productId).get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof Product)) {
            throw new ConverterException(new FacesMessage("Give object has invalid type"));
        }
        return String.valueOf(((Product) value).getId());
    }

}
