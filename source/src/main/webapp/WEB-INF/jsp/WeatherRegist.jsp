<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>天気登録画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/WeatherRegist.css">
</head>
<%@ include file="Common.jsp" %>
<body>

	<h1>天気の登録画面</h1>
	
	<div>↓登録は1日1回まで</div>
	<p>${message}</p>
	
	<button id="btn">天気取得ボタン</button>
	<img src="images/heta-cow.png" alt="牛のイラスト"> 
	<form method="POST" action="${pageContext.request.contextPath}/WeatherRegistServlet"> <div id="errorArea">${errorMsg}</div><br> 
			
			<label for="timer">登録対象日 (今日)：</label>
			<input type="text" id="timer" name="day" placeholder="日付" ><br>
			<input type="hidden" id="weatherCode" name="weatherCode" >
	<table class="tenki-table">
		<thead>
			<tr>
				<th>天気</th>
				<th>最高気温</th>
				<th>最低気温</th>
				<th>平均湿度</th>
				<th>降水量</th>
				<th>風力</th>
			</tr>
		 </thead>
		 <tbody>
			<tr>
				<td><input type="text" id="weatherText" name="tenki" placeholder="天気"readonly><br></td>
				<td><input type="text" id="highKion" name="high_kion" placeholder="最高気温"readonly><br></td>
				<td><input type="text" id="lowKion" name="low_kion" placeholder="最低気温"readonly><br></td>
				<td><input type="text" id="humidity" name="humidity" placeholder="平均湿度"readonly><br></td>
				<td><input type="text" id="precipitation" name="pre" placeholder="降水量"readonly><br></td>
				<td><input type="text" id="windSpeed" name="windpower" placeholder="風力"readonly><br></td>
			</tr>
		</tbody>
	</table>
		<button type="submit" id="send-btn" name="regist" value="weatherRegist">これで送信</button>
	</form>
	<script src="${pageContext.request.contextPath}/js/WeatherRegist.js"></script>
</body>
</html>