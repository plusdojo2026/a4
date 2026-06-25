<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>シフト更新・削除</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ShiftUpdateDelete.css">
</head>
<body>
	<%@ include file="Common.jsp"%>
	<h1>シフト更新・削除画面</h1>
	<form action="ShiftUpdateDeleteServlet" method="post" id="form">

		<div class="shift-card">

			<input type="hidden" name="id" value="${employee.id}"> <input
				type="hidden" name="day" value="${day}">

			<p>従業員名：${employee.name}</p>
			<p>変更する日付：${day}</p>

			<p>
				現在のシフト：
				<c:choose>
					<c:when test="${currentShift.intime == 0}">早朝</c:when>
					<c:when test="${currentShift.intime == 1}">朝</c:when>
					<c:when test="${currentShift.intime == 2}">昼</c:when>
					<c:when test="${currentShift.intime == 3}">夕</c:when>
					<c:when test="${currentShift.intime == 4}">休</c:when>
					<c:otherwise>未登録</c:otherwise>
				</c:choose>
			</p>

			<p>
				シフトの時間: <select name="shiftTime">
					<option value="0" ${currentShift.intime == 0 ? 'selected' : ''}>早朝</option>
					<option value="1" ${currentShift.intime == 1 ? 'selected' : ''}>朝</option>
					<option value="2" ${currentShift.intime == 2 ? 'selected' : ''}>昼</option>
					<option value="3" ${currentShift.intime == 3 ? 'selected' : ''}>夕</option>
					<option value="4" ${currentShift.intime == 4 ? 'selected' : ''}>休</option>
				</select>
			</p>

			<button type="button" onclick="history.back()">戻る</button>

			<button type="submit" name="shift_update" value="更新">更新する</button>

			<button type="submit" name="shift_delete" value="削除">削除する</button>
		</div>
	</form>

	<!-- JSの読み込み -->
	<script src="ShiftUpdateDelete.js"></script>
</body>
</html>