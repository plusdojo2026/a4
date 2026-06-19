<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>天気登録画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/WeatherRegist.css">
<script src="${pageContext.request.contextPath}/js/WeatherRegist.js" defer></script>
</head>
<%@ include file="Common.jsp" %>
<body>

	<h1>天気の登録画面</h1>
	
	<div id="timer" class="timer"></div>
	<div>WeatherDBから今日分のデータが入ってるかを確認し、○×表示</div>
	
	<button id="btn">天気取得ボタン</button>
	<img src="images/heta-cow.png" alt="牛のイラスト"> 
	<form method="POST" action="${pageContext.request.contextPath}/WeatherRegistServlet"> <div id="errorArea">${errorMsg}</div><br> <table class="tenki-table">
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
				<td><input type="text" id="weatherText" name="tenki" placeholder="天気"><br></td>
				<td><input type="text" id="highKion" name="high_kion" placeholder="最高気温"><br></td>
				<td><input type="text" id="lowKion" name="low_kion" placeholder="最低気温"><br></td>
				<td><input type="text" id="humidity" name="humidity" placeholder="平均湿度"><br></td>
				<td><input type="text" id="precipitation" name="pre" placeholder="降水量"><br></td>
				<td><input type="text" id="windSpeed" name="windpower" placeholder="風力"><br></td>
			</tr>
		</tbody>
	</table>
		<button type="submit" id="send-btn" name="regist" value="weatherRegist">これで送信</button>
	</form>
</body>
</html>