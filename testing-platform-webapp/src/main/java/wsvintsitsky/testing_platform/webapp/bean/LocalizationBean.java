package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import wsvintsitsky.testing_platform.webapp.localization.SupportedLanguagesEnum;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class LocalizationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SupportedLanguagesEnum language;
	
	public SupportedLanguagesEnum getLanguage() {
		return language;
	}

	public void setLanguage(SupportedLanguagesEnum language) {
		this.language = language;
	}

	public SupportedLanguagesEnum[] getSupportedLanguages() {
		return SupportedLanguagesEnum.values();
	}
	
	@PostConstruct
    public void init() {
        language = SupportedLanguagesEnum.ENGLISH;
    }
	
	public void localeChanged(AjaxBehaviorEvent e) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(language.getLocale());
	}
}
