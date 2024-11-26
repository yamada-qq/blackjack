<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.model.Trump" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ブラックジャックテスト</title>
</head>
<body>
    <h1>表示テスト</h1>
    <%
    Trump trumpGame = new Trump();
    List<Trump> deck = Trump.nDeckGame();
    List<Trump> dealer = new ArrayList<>();
    List<Trump> player = new ArrayList<>();

    out.println("リストの残り枚数: " + deck.size() + "<br>");
    for(int i = 0; i < 2; i++){
        dealer.add(Trump.deal(deck));
        player.add(Trump.deal(deck));
    }
    out.println("ディーラーの手札:<br>");
    for(Trump s : dealer){
        out.println(s + "<br>");
    }
    out.println("<br>プレイヤーの手札:<br>");
    for(Trump s : player){
        out.println(s + "<br>");
    }
    out.println("<br>リストの残り枚数: " + deck.size());
    %>
</body>
</html>