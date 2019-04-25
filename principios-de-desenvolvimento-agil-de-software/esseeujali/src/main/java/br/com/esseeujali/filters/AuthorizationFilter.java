package br.com.esseeujali.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.esseeujali.models.Usuario;
import br.com.esseeujali.util.token.TokenManager;

@WebFilter(filterName="authFilter")
public class AuthorizationFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpRes = (HttpServletResponse) res;
		
		String authHeader = httpReq.getHeader("Authorization");
		
		if(authHeader == null){
			httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		if(!TokenManager.isTokenAtivo(authHeader)){
			httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		Usuario usuario;
		
		try {
			usuario = TokenManager.getToken(authHeader).getUser();
		} catch (Exception e) {
			httpRes.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}
		
		req.setAttribute("usuarioLogado", usuario);
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
