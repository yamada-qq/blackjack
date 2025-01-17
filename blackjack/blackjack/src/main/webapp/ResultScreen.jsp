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
//処理前のメダル
int beforMedal = gameManagement.getOwnMedal() + gameManagement.getBetMedal();

String resultMessage = "";
int resultNumber = cardManagement.determineWinner(cardManagement);
gameManagement.processGameResult(resultNumber);
if (resultNumber == 1) {
	resultMessage = "勝ち";
} else if (resultNumber == -1) {
	resultMessage = "負け";
} else {
	resultMessage = "引き分け";
}
//処理後のメダル
int afterMedal = gameManagement.getOwnMedal();

boolean canNexMinBet = gameManagement.canContinueWithMinBet();
session.setAttribute("gamemanagement", gameManagement);
session.setAttribute("cardManagement", cardManagement);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/style.css">
<title>Insert title here</title>
</head>
<body>
<h1>ラウンド<%=gameManagement.getRound() %> 結果:<%=resultMessage%></h1>
<div class="frame">
	<!--ディーラーの手札  -->
	ディーラの手札：　合計＝<%=cardManagement.calculateHandValue(cardManagement.getDealerHand()) %>
	<table>
		<tr>
			<%for(Trump trumps:cardManagement.getDealerHand()){ %>
			<td><%=trumps %></td>
			<%} %>
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

	<h3>所持メダル：<%=beforMedal + "→" + afterMedal %></h3>
	<p>次ラウンドの最小Bet：<%=gameManagement.getMinBet() %></p>
	<%if(canNexMinBet){ %>
		<form action="BetScreen.jsp" method="post">
			<input type="submit" value="次のゲームへ">
		</form>
	<%}else{ %>
		<form action="RankingScreen.jsp" method="post">
			<input type="submit" value="ランキング画面">
		</form>
	<%} %>
</body>
</html>