<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>牛のデータ更新</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/CowsUpdateDelete.css">
</head>
<%@ include file="Common.jsp"%>
<body>
	<h1>牛のデータ更新</h1>
	<!-- ページタイトル -->
	<!-- ページ左側牛の写真 -->
	<!-- ウシのイラスト　画像なかったらcow2を表示-->
	<img src="${pageContext.request.contextPath}/images/${cow.photo}"
		width="300"
		onerror="this.src='${pageContext.request.contextPath}/images/no_image.jpg';">

	<form method="post"
		action="${pageContext.request.contextPath}/CowsUpdateDeleteServlet"
		enctype="multipart/form-data">

		<!-- numberをサーブレットへ送信するためのhidden項目を追加 -->
		<input type="hidden" name="number" value="${cow.number}">

		<table border="1">

			<tr>
				<td>ウシID</td>
				<td><input type="number" name="id" value="${cow.id}"maxlength="10"></td>
			</tr>

			<tr>
				<td>名前</td>
				<td><input type="text" name="name" value="${cow.name}">
				</td>
			</tr>

			<tr>
				<td>性別</td>
				<td><select name="gender">
						<!-- オスを 0、メスを 1 -->
						<option value="0" ${cow.gender == 0 ? 'selected' : ''}>オス</option>
						<option value="1" ${cow.gender == 1 ? 'selected' : ''}>メス</option>
				</select></td>
			</tr>

			<tr>
				<td>生年月日</td>
				<td><input type="date" name="birth_day"
					value="${cow.birth_day}"></td>
			</tr>

			<tr>
				<td>生死</td>
				<td><select name="status">
						<!-- 【修正】statusはString型のため、数値をシングルクォーテーションで囲み文字列として比較します -->
						<option value="0" ${cow.status == '0' ? 'selected' : ''}>生</option>
						<option value="1" ${cow.status == '1' ? 'selected' : ''}>死亡</option>
						<option value="2" ${cow.status == '2' ? 'selected' : ''}>出荷済</option>
				</select></td>
			</tr>

			<tr>
				<td>写真</td>
				<td>
					<!-- 現在の画像名を保持 --> <input type="hidden" name="oldPhoto"
					value="${cow.photo}"> <!--画像を入れる--> <input type="file"
					name="newPhoto">
				</td>
			</tr>

			<tr>
				<td>死亡日時</td>
				<td><input type="date" name="updatedate"
					value="${cow.updatedate}"></td>
			</tr>

			<tr>
				<td>死因</td>
				<td><select name="cause">
						<option value="老衰" ${cow.cause == '老衰' ? 'selected' : ''}>老衰</option>
						<option value="病死" ${cow.cause == '病死' ? 'selected' : ''}>病死</option>
						<option value="事故死" ${cow.cause == '事故死' ? 'selected' : ''}>事故死</option>
						<option value="死産" ${cow.cause == '死産' ? 'selected' : ''}>死産</option>
						<option value="その他" ${cow.cause == 'その他' ? 'selected' : ''}>その他</option>
				</select></td>
			</tr>

			<tr>
				<td>登録日</td>
				<td><input type="date" name="regist_day"
					value="${cow.regist_day}"></td>
			</tr>

		</table>

		<button type="submit" name="submit" value="更新">変更</button>
		<button type="submit" name="submit" value="削除">削除</button>

	</form>
	<!-- JSの読み込み -->
	<script src="CowsUpdateDelete.js"></script>

</body>
</html>