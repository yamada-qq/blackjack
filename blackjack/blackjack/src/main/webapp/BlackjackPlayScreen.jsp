<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.co.Blackjack.management.*"%>
<%@ page import="jp.co.Blackjack.modl.*"%>

<%
GameManagement gameManagement = (GameManagement) session.getAttribute("gamemanagement");
CardManagement cardManagement = (CardManagement) session.getAttribute("cardManagement");

if (gameManagement == null || cardManagement == null) {
    response.sendRedirect("GameStart");
    return;
}

%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/style.css">
<title>ブラックジャックゲーム</title>
</head>
<body>
    <h1>ラウンド<%=gameManagement.getRound() %> :進行中</h1>
    <div class="frame">
	<!--ディーラーの手札  -->
	ディーラの手札<br>
	<table>
		<tr>
			<td><%=cardManagement.getDealerHand().get(0) %></td>
			<td>&nbsp;</td>
		</tr>
	</table>

	<!--プレイヤーの手札  -->
	プレイヤーの手札：　合計＝<%=cardManagement.calculateHandValue(cardManagement.getPlayerHand()) %><br>
	<table>
		<tr>
		<%for(Trump trumps:cardManagement.getPlayerHand()){ %>
			<td><%=trumps %></td>
			<%} %>
		</tr>
	</table>
	</div>

    <form action="PlayerAction" method="post">
    <%if(cardManagement.calculateHandValue(cardManagement.getPlayerHand()) < 21){ %>
        <button type="submit" name="action" value="hit">Hit（もう一枚引く）</button>
        <%} %>
        <button type="submit" name="action" value="stand">Stand（勝負する）</button><br>

        <%if(cardManagement.getPlayerHand().size() <= 2){ %>
        	<%if(gameManagement.getBetMedal() < gameManagement.getOwnMedal()){ %>
            <button type="submit" name="action" value="doubledown">Double Down（BETを2倍にして、カードを1枚だけ引く）</button><br>
            <%} %>
            <button type="submit" name="action" value="surrender">Surrender（BETの半額受け取り次ゲームへ）</button>
        <%} %>
    </form>

</body>
</html>