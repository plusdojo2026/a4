<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css"><!-- CSS読み込み -->
</head>
<%@ include file="Common.jsp" %>
<body>

<c:if test="${not empty msg}">
		<div class="info-message">
			${msg}
		</div>
	</c:if>

	<div class="News">
	<h2>お知らせ</h2><!-- その他のお知らせ -->
	<h3>えさの在庫</h3>
		<ul>
			<li>えさの総量から取得</li>
				<p>${total}</p>
		</ul>
	<h3>空調指示</h3>
		<ul>
			<li>天気から取得</li>
			<li>${air1}</li>
		</ul>
	<h3>窓の開け閉め</h3>
		<ul>
			<li>天気から取得</li>
			<li>${window}</li>
		</ul>
	<h3>飲料水の量</h3>
		<ul>
			<li>天気から取得</li>
			<li>${drink}</li>
		</ul>
	</div>
	<div class="CowNews">
	<h2>牛に関するお知らせ</h2>		<!-- 5牛に関するお知らせ -->
		<!-- <h3>体調不良の牛</h3> -->
			<ul>
			
			
			
			<c:choose>
				<c:when test="${not empty badCowNames}">
				体調不良の可能性がある牛は<br>
				<c:forEach var="name" items="${badCowNames}">
				・ ${name}です<br>
				</c:forEach>
				</c:when>
				<c:otherwise>
				みんな元気です
				</c:otherwise>
				
			</c:choose>
			</ul>
		<h3>出荷時期</h3>
			<ul>
				<li>牛データの年齢、性別から取得</li>
			</ul>
	</div>
</body>
</html>