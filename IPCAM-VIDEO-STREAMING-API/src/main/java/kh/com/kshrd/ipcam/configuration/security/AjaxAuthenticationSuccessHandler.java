package kh.com.kshrd.ipcam.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("ajaxAuthenticationSuccessHandler")
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
			response.getWriter().print(determineTargetUrl(auth));
	        response.getWriter().flush();

	}



	private String determineTargetUrl(Authentication authentication) {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority authority : authorities) {
			System.out.println(authority.getAuthority());
			roles.add(authority.getAuthority());
			System.out.println("Extract Role: " + authority.getAuthority());
		}
		if (roles.contains("ROLE_ADMIN")) {
			return "swagger";
		}
		else{
			return "accessDenied";
		}

	}

}
