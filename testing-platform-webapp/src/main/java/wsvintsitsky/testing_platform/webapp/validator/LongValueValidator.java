package wsvintsitsky.testing_platform.webapp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("wsvintsitsky.testing_platform.webapp.validator.LongValueValidator")
public class LongValueValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value.toString().isEmpty()) {
			FacesMessage message = new FacesMessage("value must not be empty", "value must not be empty");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		try {
			Long.parseLong(value.toString());
		} catch (NumberFormatException exception) {
			FacesMessage message = new FacesMessage("value must be interger", "value must be interger");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}
