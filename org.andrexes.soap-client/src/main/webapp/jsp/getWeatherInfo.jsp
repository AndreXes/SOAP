<%@page import="org.andrexes.soap.client.service.WeatherService"%>
<html>
<body>
<h2>WeatherForecastByZIP!!!</h2>
<p>
<%=WeatherService.getWeatherForecastForZIP(request.getParameter("ZIPCode")).replaceAll(System.lineSeparator(), "<br>") %>
</body>
</html>
