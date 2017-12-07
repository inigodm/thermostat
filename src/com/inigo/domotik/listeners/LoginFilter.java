package com.inigo.domotik.listeners;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/site/*")
public class LoginFilter implements Filter {

    /**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		System.out.println("ADRESS " + request.getRemoteAddr());
		if (request.getRemoteAddr().contains("127.0.0.1") 
				|| request.getRemoteAddr().contains("localhost")
				|| request.getRemoteAddr().contains("0:0:0:0:0:0:0:1")){
			session.setAttribute("user", "inigo");
		}
		if (session.getAttribute("user") == null){
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("/Thermostat/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
