package devcast.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

/**
 * @author mzielinski on 21.12.14.
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    private Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!pattern.matcher((CharSequence) value).matches()) {
            throw new ValidatorException(new FacesMessage(String.format("Given email address [%s] is not valid", value)));
        }

    }

}
