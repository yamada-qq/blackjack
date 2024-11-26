<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="CSS/style.css">
<title>Insert title here</title>
</head>
<body>
	<h1>難易度を選択して下さい</h1>
	<form action="GameStart" method="post">
	やさしい<input type="radio" name="level" value="1" checked><br>
	普通<input type="radio" name="level" value="2"><br>
	難しい<input type="radio" name="level" value="3"><br>
	<input type="submit" value="スタート">
	</form>
</body>
</html>