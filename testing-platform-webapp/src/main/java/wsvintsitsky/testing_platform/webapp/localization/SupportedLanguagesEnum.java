package wsvintsitsky.testing_platform.webapp.localization;

import java.util.Locale;

public enum SupportedLanguagesEnum {

	ENGLISH(new Locale("en", "US")), RUSSIAN(new Locale("ru", "RU"));
	
	private Locale locale;
	
	private SupportedLanguagesEnum(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}
}
