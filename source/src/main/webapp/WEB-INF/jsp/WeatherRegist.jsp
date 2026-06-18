<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>天気登録画面</title>
<!-- CSS読み込み -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/WeatherRegist.css">
</head>
<%@ include file="Common.jsp" %>
<body>
	<!-- ページタイトル左上 -->
	<h1>天気の登録画面</h1>
	
	<!-- 画面右上 -->>
	<h3>今日の日付</h3>
		<div>tenki method js de yaru</div>
		<div>WheatherDBから今日分のデータが入ってるかを確認し、○×表示</div>
		
	<!-- 画面左側 -->	
	<!-- ボタンを押すとJSが動く 次のフォーム部分に入る　-->
	<button id = "get-weather-btn">天気取得ボタン</button>>
	<img src="images/heta-cow.png" alt="牛のイラスト">	<!-- ウシのイラスト-->
	
	<!-- 画面右真ん中~下 取得したデータが自動で入る+書き込みも可能なform-->>
	<form method="POST" action="/WeatherRegistServlet">	<!-- ログインフォーム。ログインサーブレットにポストされます -->
    	<div id="errorArea">${errorMsg} </div><br>	<!-- エラーメッセージ表示欄 -->
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
		     	<td><input type="text" name="tenki" placeholder="天気"><br></td>
		     	<td><input type="text" name="high_kion" placeholder="最高気温"><br></td>
		     	<td><input type="text" name="low_kion" placeholder="最低気温"><br></td>
		     	<td><input type="text" name="humidity" placeholder="平均湿度"><br></td>
		     	<td><input type="text" name="pre" placeholder="降水量"><br></td>
		     	<td><input type="text" name="windpower" placeholder="風力"><br></td>
		   	</tr>
		</tbody>
	</table>
		<button type="submit" id = send-weather-btn name="regist" value="weatherRegist">これで送信</button>
	</form>
<!-- JSの読み込み -->
<script src="WeatherRegist.js"></script>
</body>
</html>