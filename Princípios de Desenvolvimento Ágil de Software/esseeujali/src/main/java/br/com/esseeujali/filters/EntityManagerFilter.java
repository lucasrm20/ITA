package br.com.esseeujali.filters;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName="entityManagerFilter")
public class EntityManagerFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		req.setAttribute("entityManager", factory);
		
		chain.doFilter(req, res);
		
		factory.close();
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
