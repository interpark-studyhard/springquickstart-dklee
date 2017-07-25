package com.yidigun.beforespring;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugHttpServletRequest extends HttpServletRequestWrapper {
	
	private static final Logger logger = LoggerFactory.getLogger(DebugHttpServletRequestFilter.class);

	private Logger paramLogger = null;

	public DebugHttpServletRequest(HttpServletRequest request, String loggerName) {
		super(request);
		paramLogger = LoggerFactory.getLogger(loggerName);
		logger.debug("wrapping DebugHttpServletRequest");
	}

	@Override
	public String getParameter(String name) {
		paramLogger.debug("getParameter(\"{}\")", name);
		return super.getParameter(name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map getParameterMap() {
		paramLogger.debug("getParameterMap()");
		return super.getParameterMap();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getParameterNames() {
		paramLogger.debug("getParameterNames()");
		return super.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		paramLogger.debug("getParameterValues(\"{}\")", name);
		return super.getParameterValues(name);
	}

}
