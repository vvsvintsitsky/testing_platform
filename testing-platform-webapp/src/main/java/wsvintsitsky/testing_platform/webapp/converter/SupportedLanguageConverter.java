package wsvintsitsky.testing_platform.webapp.converter;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.webapp.localization.SupportedLanguagesEnum;

@Component
@Scope("singleton")
public class SupportedLanguageConverter implements Serializable, Converter {

	private static final long serialVersionUID = 1L;

	@Override
	    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
	        if (submittedValue == null || submittedValue.isEmpty()) {
	            return null;
	        }

	        try {
	            return SupportedLanguagesEnum.valueOf(submittedValue);
	        } catch (NumberFormatException e) {
	            throw new ConverterException(new FacesMessage(String.format("%s is not a valid SupportedLanguage", submittedValue)), e);
	        }
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
	        if (modelValue == null) {
	            return "";
	        }

	        if (modelValue instanceof SupportedLanguagesEnum) {
	            return ((SupportedLanguagesEnum) modelValue).name();
	        } else {
	            throw new ConverterException(new FacesMessage(String.format("%s is not a valid SupportedLanguage", modelValue)));
	        }
	    }
}