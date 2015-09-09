<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
	<CENTER>
				<H1>Car Configuration Result Page</H1>
				<%
					String modelname = (String) session.getAttribute("autoname");
					Float baseprice = (Float) session.getAttribute("baseprice");
					Integer length = (Integer) session.getAttribute("size");
					String[] res = new String[length];
					String[] opt = new String[length];
					for(int i = 0; i < length; i++){
						res[i] = (String) session.getAttribute("Choice" + i);
						opt[i] = (String) session.getAttribute("Optset" + i);
					}
				%>
				<form method="post" action="OptionsetSer">
				<table border = "1" cellpadding="8" width="500">
						<tr>
						<td><b>ModelName</b></td>
						<td align="right"><b><%out.println(modelname); %></b></td></tr>
						<tr>
						<td>BasePrice</b></td>
						<td align="right"><b><%out.println(baseprice); %></td>
						</tr>
						<tr>
							<% for(int i = 0; i < length; i++){%>
							<td><%=opt[i]%></td>
							<td align="right"><%out.println(res[i]); %></td></tr>
						<%} %>
				</table>
			</form>
		</CENTER>
</body>
</html>