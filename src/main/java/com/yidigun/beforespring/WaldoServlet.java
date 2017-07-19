package com.yidigun.beforespring;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;

public class WaldoServlet extends HttpServlet {

	private static final long serialVersionUID = -2348053283345030401L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String lang = ServletRequestUtils.getStringParameter(req, "lang", "en");
		Locale locale = new Locale(lang);
		String charset = "UTF-8";

		ResourceBundle messages = ResourceBundle.getBundle("message", locale);

		String time = messages.getString("morning");
		String adjective = messages.getString("mightyfine");
		String name = messages.getString("waldo");
		
		String introduce = MessageFormat.format(messages.getString("introduce"), name);
		String greeting = MessageFormat.format(messages.getString("greeting"), adjective, time, introduce);

		
		res.setContentType("text/html");
		res.setCharacterEncoding(charset);

		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"" + charset + "\">");
		out.println("<title>" + adjective + " " + time + "</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + greeting + "</p>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
