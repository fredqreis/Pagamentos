package br.com.pagamentos.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pagamentos.controller.UsuarioBean;

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

	@Inject
	private UsuarioBean usuario;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		
		if (!usuario.isLogado() && !servletRequest.getRequestURI().endsWith("/Login.xhtml")
				&& !servletRequest.getRequestURI().contains("/javax.faces.resource/")) {
			servletResponse.sendRedirect(servletRequest.getContextPath() + "/Login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
