package wsvintsitsky.testing_platform.webapp.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("wsvintsitsky.testing_platform.webapp.validator.DateValidator")
public class DateValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null || value.toString().isEmpty()) {
			FacesMessage message = new FacesMessage("value must not be empty", "value must not be empty");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

		String pattern = "dd.MM.yyyy";
		try {
			@SuppressWarnings("unused")
			Date date = (Date) value;
		} catch (ClassCastException e) {
			String errorMessage = String.format("value must be like %s", pattern);
			FacesMessage message = new FacesMessage(errorMessage, errorMessage);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
