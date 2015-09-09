<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Client.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MainPage</title>
</head>
<body>
<CENTER>
	<H1>Car Configuration Main Page</H1>
			<form method="post" action="OptionsetSer">
				<table border = "1" cellpadding="8" width="300">
						<tr>
						<b>Please Select a model</b>
						<% 
						String str = (String) session.getAttribute("Modelist"); 
						String[] modelname = str.substring(1, str.length() - 1).split(",");
						for(int i = 0; i < modelname.length; i++){%>
						<tr>
						<td><%out.println(modelname[i] + "<td>"); %>
						<input type="radio" name="Choice" value="<%=modelname[i]%>" checked></td>
						</tr>
						<%} %>
					<tr><td/>
					<td align="right"><input type="submit" value="Done"></td></tr>
				</table>
			</form>
	</CENTER>
</body>
</html>