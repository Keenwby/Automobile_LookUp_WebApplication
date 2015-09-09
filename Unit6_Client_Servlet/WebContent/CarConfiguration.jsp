<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<CENTER>
			<H1>Car Configuration Option Page</H1>
			<%
				Automobile auto = (Automobile) session.getAttribute("auto");
				ArrayList<String> optsetlist = auto.getOptsetlist();
			%> 
			<form method="post" action="ResultSer">
				<table border = "3" cellpadding="8" width="300">
						<tr><b><%out.println(auto.getModel()); %></b></tr>
						<% for(int i = 0; i < optsetlist.size(); i++){%>
						<tr>
						<td><%String optsetname = optsetlist.get(i);
							  out.println(optsetname); %></td>
						<td align="right"><select name="<%=optsetname%>">
								<% 
									ArrayList<String> optlist = auto.getOptlist(optsetname);
									for(int j = 0 ; j < optlist.size() ; j++)
									{
										String optname = optlist.get(j) ;
									%>
										<option value="<%=optname%>"><%=optname%></option>
									<%
									}
									%>
								</select>
							</tr>
						<%} %>
					<td></td><td align="right"><input type="submit" value="Done"></td>
				</table>
			</form>		
</CENTER>
</body>
</html>


