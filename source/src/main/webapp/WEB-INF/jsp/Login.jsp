<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css">
</head>
<body>
	<img src="logo.png" alt="神牛乳のロゴ">		<!-- 神牛乳のロゴ -->
	<form method="POST" action="/a4/LoginServlet">	<!-- ログインフォーム。ログインサーブレットにポストされます -->
 		ID<input type="text" name="id"><br>		<!-- id入力 -->
    	パスワード<input type="password" name="pw"><br>		<!-- パスワード入力 -->
    	<div id="errorArea"></div><br>	<!-- エラーメッセージ表示欄 -->
    	<button type="submit" name="login" value="ログイン"></button>	<!-- ログインデータ送信 -->
	</form>
</body>
</html>