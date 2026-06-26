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
	<form action="CowsRegistServlet" id="myForm" method="post" enctype="multipart/form-data">

		<div style="color: red; font-weight: bold; margin-bottom: 10px;">${errorMsg}</div>

		ウシID:<input type="text" name="id" id="cow_id" maxlength="10" value="${id}">
		<span id="error_message" style="color: red; font-weight: bold; margin-left: 10px;"></span>
		
		名前： <input type="text" name="name" id="name" value="${name}">
		
		性別:<select name="gender" id="gender">
			<option value="0" ${gender == '0' ? 'selected' : ''}>オス</option>
			<option value="1" ${gender == '1' ? 'selected' : ''}>メス</option>
		</select>
		
		生年月日： <input type="date" name="birth_day" id="birth_day" value="${birth_day}">
		
		生死:<select name="status" id="status">
			<option value="0" ${status == '0' ? 'selected' : ''}>生</option>
			<option value="1" ${status == '1' ? 'selected' : ''}>死</option>
			<option value="2" ${status == '2' ? 'selected' : ''}>移動</option>
		</select>
		
		写真： <input type="file" name="photo" id="photo">

		<button type="submit" id="submit">登録</button>

	</form>
</div>
	<!-- JSの読み込み -->
	<script src="CowsRegist.js"></script>
</body>
</html>