<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="jp.co.Blackjack.management.*"%>
<%
GameManagement gameManagement = (GameManagement) request.getAttribute("gamemanagement");
out.println(gameManagement);//デバック
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>画面遷移成功</h1>
</body>
</html>