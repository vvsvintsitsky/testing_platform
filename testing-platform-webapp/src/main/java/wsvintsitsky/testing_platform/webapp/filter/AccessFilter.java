package wsvintsitsky.testing_platform.webapp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;

public class AccessFilter implements Filter {

	@Value("${path.redirect.loginPage}")
	private String loginRedirectPath;
	
	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		if(session != null) {
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			if(userCredentials != null) {
				chain.doFilter(httpRequest, httpResponse);
			}
		} else {
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + loginRedirectPath);
		}
		return;
	}

	@Override
	public void destroy() {
	}
}
