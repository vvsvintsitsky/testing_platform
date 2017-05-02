package wsvintsitsky.testing_platform.webapp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("wsvintsitsky.testing_platform.webapp.validator.NotEmptyStringValidator")
public class NotEmptyStringValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String toValidate = value.toString();

		if (toValidate == null || toValidate.isEmpty()) {
			FacesMessage message = new FacesMessage("value must not be empty", "value must not be empty");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}
