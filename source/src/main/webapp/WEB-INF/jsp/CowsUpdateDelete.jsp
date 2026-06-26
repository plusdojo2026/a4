<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>牛のデータ更新</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CowsUpdateDelete.css">
</head>
<%@ include file="Common.jsp"%>
<body>
	<h1>牛のデータ更新</h1>
	
	<div class="photo">
		<img src="${pageContext.request.contextPath}/images/${cow.photo}" width="300"
			onerror="this.src='${pageContext.request.contextPath}/images/no_image.jpg';">
	</div>

	<!-- エラーメッセージを表示 -->
	<div style="color: red; font-weight: bold; margin-bottom: 10px;">${errorMsg}</div>

	<form method="post" action="${pageContext.request.contextPath}/CowsUpdateDeleteServlet" enctype="multipart/form-data">
		<div class="employees-card">

			<!-- 常にサーブレットから届く cow.number を確実に送信する -->
			<input type="hidden" name="number" value="${cow.number}">

			<table border="1">
				<tr>
					<td>ウシID</td>
					<td>
						<!-- シンプルに cow.id を表示 -->
						<input type="text" id="cow_id" name="id" value="${cow.id}" maxlength="10" required>
						<span id="error_message" style="color: red;"></span>
					</td>
				</tr>

				<tr>
					<td>名前</td>
					<td><input type="text" name="name" value="${cow.name}"></td>
				</tr>

				<tr>
					<td>性別</td>
					<td>
						<select name="gender">
							<option value="0" ${cow.gender == 0 ? 'selected' : ''}>オス</option>
							<option value="1" ${cow.gender == 1 ? 'selected' : ''}>メス</option>
						</select>
					</td>
				</tr>

				<tr>
					<td>生年月日</td>
					<td><input type="date" name="birth_day" value="${cow.birth_day}"></td>
				</tr>

				<tr>
					<td>生死</td>
					<td>
						<select name="status">
							<option value="0" ${cow.status == '0' ? 'selected' : ''}>生</option>
							<option value="1" ${cow.status == '1' ? 'selected' : ''}>死亡</option>
							<option value="2" ${cow.status == '2' ? 'selected' : ''}>出荷済</option>
						</select>
					</td>
				</tr>

				<tr>
					<td>写真</td>
					<td>
						<input type="hidden" name="oldPhoto" value="${cow.photo}">
						<input type="file" name="newPhoto">
					</td>
				</tr>

				<tr>
					<td>死亡日時</td>
					<td><input type="date" name="updatedate" value="${cow.updatedate}"></td>
				</tr>

				<tr>
					<td>死因</td>
					<td>
						<select name="cause">
							<option value="老衰" ${cow.cause == '老衰' ? 'selected' : ''}>老衰</option>
							<option value="病死" ${cow.cause == '病死' ? 'selected' : ''}>病死</option>
							<option value="事故死" ${cow.cause == '事故死' ? 'selected' : ''}>事故死</option>
							<option value="死産" ${cow.cause == '死産' ? 'selected' : ''}>死産</option>
							<option value="その他" ${cow.cause == 'その他' ? 'selected' : ''}>その他</option>
						</select>
					</td>
				</tr>

				<tr>
					<td>登録日</td>
					<td><input type="date" name="regist_day" value="${cow.regist_day}"></td>
				</tr>
			</table>

			<button type="submit" name="submit" value="update">変更</button>
			<button type="submit" name="submit" value="Delete">削除</button>
		</div>
	</form>

	<script src="CowsUpdateDelete.js"></script>
</body>
</html>