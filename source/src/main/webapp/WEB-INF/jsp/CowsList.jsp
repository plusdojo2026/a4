<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="Common.jsp"%>
<title>ウシ一覧</title>
<!-- CSS読み込み -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CowList.css">
</head>
<body>

	<h1>ウシ一覧リスト</h1>
	<!-- 見本の表 -->
	<div class="example-cow-card">

		<ul>
			<p>見本</p>
			<img src="${pageContext.request.contextPath}/images/cow2.jpg"
				alt="画像なし" width="200">
			<li>個体ID: 0</li>
			<li>名前: うしの名前</li>
			<li>性別: オス</li>
			<li>生年月日: 2000-01-01</li>
			<li>生死: 生</li>
		</ul>
		<button type="submit" name="edit" value="編集">編集（これは見本）</button>
	</div>

	<!-- サーブレットから受け取ったcowsListの数だけ繰り返し処理 -->
	<c:forEach var="cow" items="${cowsList}">
		<div class="cow-card">

			<img src="${pageContext.request.contextPath}/images/${cow.photo}"
				alt="${cow.name}" width="200"
				onerror="this.src='${pageContext.request.contextPath}/images/no_image.jpg';">

			<ul>
				<!--EL式を使って、ウシ1頭ずつのデータを表示 -->
				<li>個体ID: ${cow.id}</li>
				<li>名前: ${cow.name}</li>

				<li><c:choose>
						<c:when test="${cow.gender == 0}">オス</c:when>
						<c:when test="${cow.gender == 1}">メス</c:when>
						<c:otherwise>不明</c:otherwise>
					</c:choose></li>

				<li>生年月日: ${cow.birth_day}</li>

				<li><c:choose>
						<c:when test="${cow.status == '0'}">生</c:when>
						<c:when test="${cow.status == '1'}">死</c:when>
						<c:when test="${cow.status == '2'}">移動</c:when>
						<c:otherwise>不明</c:otherwise>
					</c:choose></li>

			</ul>

			<!-- 編集ボタン。どのウシを編集するか分かるように、valueに主キーのnumberを仕込む -->
			<!-- ウシ一頭ずつをフォームに入れる -->
			<form action="CowsListServlet" method="POST">
				<!-- 主キーを送信 -->
				<input type="hidden" name="number" value="${cow.number}">
				<button type="submit">編集</button>
			</form>
		</div>
	</c:forEach>


	<!-- JSの読み込み -->
	<script src="CowList.js"></script>
</body>
</html>