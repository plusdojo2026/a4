<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ウシ個体登録画面</title>
<!-- CSS読み込み -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CowsRegist.css">
</head>
<%@ include file="Common.jsp"%>
<body>
	<!-- ページタイトル左上 -->
	<div class="title-container">
	<h1>🆕 ウシ個体の登録画面 🐄</h1>
	</div>
	<!-- 画面左側 -->
	<div class="cows-born">
	<h3>新しいウシ！ おめでとう</h3>
	<img src="cow2.png" alt="牛のイラスト">
	<!-- ウシのイラスト-->
	<!-- 画面右上側 -->
	<table class="mihon-table">
		<thead>
			<tr>
				<th>ウシID(10ケタ)</th>
				<th>名前</th>
				<th>性別</th>
				<th>生年月日</th>
				<th>生死(自動)</th>
				<th>写真</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1000000000</td>
				<td>ウシタロウ</td>
				<td>メス</td>
				<td>2026/06/11</td>
				<td>生</td>
				<td>ファイル名</td>
			</tr>
		</tbody>
	</table>

	<!-- 画面右下側 -->
	<form action="CowsRegistServlet" method="post"
		enctype="multipart/form-data">

		<!-- 【頭の0が消えるのを防ぐため、type="number"から"text"に変更し、10文字制限を追加 -->
		ウシID:<input type="number" name="id" maxlength="10"> 名前： <input
			type="text" name="name"> 性別： <select name="gender">
			<option value="0">オス</option>
			<option value="1">メス</option>
		</select> 生年月日： <input type="date" name="birth_day"> 生死： <select
			name="status">
			<option value="0">生</option>
			<option value="1">死</option>
			<option value="2">移動</option>
		</select> 写真： <input type="file" name="photo">


		<button type="submit">登録</button>

	</form>
</div>
	<!-- JSの読み込み -->
	<script src="CowsRegist.js"></script>
</body>
</html>