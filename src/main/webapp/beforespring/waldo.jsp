<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="
java.text.MessageFormat,
java.util.Locale,
java.util.ResourceBundle,
org.springframework.web.bind.ServletRequestUtils
" %>
<%
String lang = ServletRequestUtils.getStringParameter(request, "lang", "en");
Locale locale = new Locale(lang);
String charset = "UTF-8";

ResourceBundle messages = ResourceBundle.getBundle("message", locale);

String time = messages.getString("morning");
String adjective = messages.getString("mightyfine");
String name = messages.getString("waldo");

String introduce = MessageFormat.format(messages.getString("introduce"), name);
String greeting = MessageFormat.format(messages.getString("greeting"), adjective, time, introduce);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= adjective %> <%= time %></title>
</head>
<body>
<p><%= greeting %></p>
</body>
</html>
