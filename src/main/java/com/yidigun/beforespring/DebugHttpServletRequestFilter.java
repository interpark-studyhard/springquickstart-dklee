package com.yidigun.beforespring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class DebugHttpServletRequestFilter implements Filter {
	
	public static final String DEFAULT_LOGGER_NAME = DebugHttpServletRequest.class.getName();
	
	private String loggerName;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		loggerName = filterConfig.getInitParameter("loggerName");
		if (loggerName == null)
			loggerName = DEFAULT_LOGGER_NAME;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ServletRequest wrappedReq = new DebugHttpServletRequest((HttpServletRequest)request, loggerName);
		chain.doFilter(wrappedReq, response);
	}

	@Override
	public void destroy() {
	}

}
