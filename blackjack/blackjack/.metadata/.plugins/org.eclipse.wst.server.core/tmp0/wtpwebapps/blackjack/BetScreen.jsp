<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.co.Blackjack.management.*"%>

<%
GameManagement gameManagement = (GameManagement) session.getAttribute("gamemanagement");
out.println(gameManagement); // デバッグ用
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ベット画面</title>
</head>
<body>
    <form action="BlackjackPlay" method="post">
    持ちメダル<%= gameManagement.getOwnMedal()%><br>
    BET額<select name="betValue">
    <%for(int i = gameManagement.getMinBet() ; i <= gameManagement.getOwnMedal(); i++){ %>
        <option value=<%= i%>><%=i %></option>
    <%} %>
    </select>
    <input type="submit" value="ＢＥＴを確定してゲームスタート" >
    </form>
</body>
</html>