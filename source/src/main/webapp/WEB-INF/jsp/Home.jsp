<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css"><!-- CSS読み込み -->
</head>
<%@ include file="Common.jsp" %>
<body>
	<div class="News">
	<h2>お知らせ</h2><!-- その他のお知らせ -->
	<h3>えさの在庫</h3>
		<ul>
			<li>えさの総量から取得</li>
		</ul>
	<h3>空調指示</h3>
		<ul>
			<li>天気から取得</li>
		</ul>
	<h3>窓の開け閉め</h3>
		<ul>
			<li>天気から取得</li>
		</ul>
	<h3>飲料水の量</h3>
		<ul>
			<li>天気から取得</li>
		</ul>
	</div>
	<div class="CowNews">
	<h2>牛に関するお知らせ</h2>		<!-- 5牛に関するお知らせ -->
		<h3>体調不良の牛</h3>
			<ul>
				<li>牛データ（日別）から取得</li>
			</ul>
		<h3>出荷時期</h3>
			<ul>
				<li>牛データの年齢、性別から取得</li>
			</ul>
	</div>
	<img src="photo.jpg" alt="牛の画像">	<!-- 牛のロゴ まだ何もない-->
</body>
</html>