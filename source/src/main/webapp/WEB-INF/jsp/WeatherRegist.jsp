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
<body>
	<!-- ページタイトル左上 -->
	<h1>天気の登録画面</h1>
	
	<!-- 画面右上 -->>
	<h3>今日の日付</h3>
		<div>tenki method js de yaru</div>
		<div>WheatherDBから今日分のデータが入ってるかを確認し、○×表示</div>
		
	<!-- 画面左側 -->	
	<button　id = "get-weather-btn">天気取得ボタン</button>>
	<img src="cow1.jpg" alt="牛のイラスト">	<!-- ウシのイラスト-->
	
	<!-- 画面右真ん中~下 取得したデータが自動で入る+書き込みも可能なform-->>
	<form>
		<table class="mihon-table">
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
		     	<td>tenki</td>
		     	<td>0</td>
		     	<td>0</td>
		     	<td>%</td>
		     	<td>mm</td>
		     	<td>m/s</td>
		   	</tr>
		</tbody>
	</table>
		<button　id = send-weather-btn>これで送信</button>
	</form>
<!-- JSの読み込み -->
<script src="WeatherRegist.js"></script>
</body>
</html>