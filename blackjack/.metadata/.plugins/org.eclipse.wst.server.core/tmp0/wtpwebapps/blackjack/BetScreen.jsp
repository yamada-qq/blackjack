<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.Blackjack.management.*"%>

<%
GameManagement gameManagement = (GameManagement) session.getAttribute("gamemanagement");
if (gameManagement == null) {
    response.sendRedirect("GameStart");
    return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/style.css">
<title>ベット画面</title>
</head>
<body>
    <h1>ラウンド<%=gameManagement.getRound() %> : ベット画面</h1>
    <div class="frame"></div>
    <p>持ちメダル: <%= gameManagement.getOwnMedal()%></p>
    <form action="BlackjackPlay" method="post">
        <input type="hidden" name="betValue" value="<%= gameManagement.getOwnMedal() %>">
        <input type="submit" value="ALL IN (<%= gameManagement.getOwnMedal() %>)">
    </form>
    <form action="BlackjackPlay" method="post">
        <label for="betValue">BET額(最小Bet額：<%=gameManagement.getMinBet() %>)</label>
        <select name="betValue" id="betValue">
        <% for(int i = gameManagement.getMinBet(); i <= gameManagement.getOwnMedal(); i++) { %>
            <option value="<%= i %>"><%= i %></option>
        <% } %>
        </select><br>
        <input type="submit" value="ＢＥＴを確定してゲームスタート">
    </form>
</body>
</html>