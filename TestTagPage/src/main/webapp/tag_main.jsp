<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Tag.*"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="TagController">
		<div>
			<%
				DB_Connect dc = new DB_Connect();
				
				String _tag = request.getParameter("tag_name_input");
				ArrayList<Integer> ar  = dc.TagSearch(_tag);
				for(Integer i:ar){
			%>
			<div>
				<a>
					#<%= _tag %>
				</a>
			</div>
			<div>
				<a> 가게코드: <%= i %> </a>
			</div>
			<%
				}
			%>
		</div>
		<div id="divtag_input">
			<span>#</span>
			<input type="text" id="tag_name_input" name="tag_name_input">
			<input type="submit" value="전송">
		</div>
	</form>
</body>
</html>