<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login.css">
</head>

<body>
	<div class ="login-container">
	<div class="cow-graphic cowleft">
		<img src="images/ushi_banzai.png" >
	</div>
	
	<div class="cow-graphic cowright">
		<img src="images/ushi_banzai.png" >
	</div>
</div>

	<img src="images/logo000.png" alt="神牛乳のロゴ">		<!-- 神牛乳のロゴ -->
	<form method="POST" action="/a4/LoginServlet">	<!-- ログインフォーム。ログインサーブレットにポストされます -->
 		ID<input type="text" name="id"><br>		<!-- id入力 -->
    	パスワード<input type="password" name="pw"><br>		<!-- パスワード入力 -->
    	<div id="errorArea">${errorMsg} </div><br>	<!-- エラーメッセージ表示欄 -->
    	<button type="submit" name="login" value="ログイン"></button>	<!-- ログインデータ送信 -->
	</form>
	
	<script src="Login.js"></script><!-- js接続 -->
</body>
</html>