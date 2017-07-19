package com.yidigun.beforespring;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;

public class Waldo2Servlet extends HttpServlet {

	private static final long serialVersionUID = -2348053283345030401L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String lang = ServletRequestUtils.getStringParameter(req, "lang", "en");
		Locale locale = new Locale(lang);

		ResourceBundle messages = ResourceBundle.getBundle("message", locale);

		String time = messages.getString("morning");
		String adjective = messages.getString("mightyfine");
		String name = messages.getString("waldo");
		
		String introduce = MessageFormat.format(messages.getString("introduce"), name);
		String greeting = MessageFormat.format(messages.getString("greeting"), adjective, time, introduce);
		
		req.setAttribute("greeting", greeting);
		req.setAttribute("adjective", adjective);
		req.setAttribute("time", time);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/beforespring/waldo.jsp");
		rd.forward(req, res);
	}

}
