package wsvintsitsky.testing_platform.webapp.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.service.UserService;

@Component
@Scope("view")
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	
	private String password;
	
	@Value("${path.redirect.successLogin}")
	private String successLoginRedirectPath;
	
	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	@Inject
	private UserService userService;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void authenticate() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		UserCredentials userCredentials = userService.findByEmailAndPassword(email, password, true);
		if(userCredentials != null) {
			String basePath = ((ServletContext)(fc.getExternalContext().getContext())).getContextPath();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(true);
			session.setAttribute(authenticatedUserAttributeName, userCredentials);
			fc.getExternalContext().redirect(basePath + successLoginRedirectPath);
		} else {
			fc.addMessage("loginForm", new FacesMessage("wrong login or password", "wrong login or password"));
		}
		return;
	}

}
