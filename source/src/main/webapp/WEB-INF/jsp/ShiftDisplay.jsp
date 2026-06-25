<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>シフト更新画面</title>
<!-- CSS読み込み -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ShiftDisplay.css">
</head>
<%@ include file="Common.jsp"%>
<body>
	<h1>🔔 シフト一覧・登録画面 🐮</h1>
	<div id="errorArea">${errorMsg}</div>
	<div>${msg}</div>
	<!-- メッセージ表示欄 -->

	<form id="update_form" action="ShiftDisplayServlet" method="post">
	<div>シフト変更</div>
		<!-- 更新者名選択 -->
		<select id="update_employee" name="id">
			<option value="">従業員を選択してください</option>
			<c:forEach var="emp" items="${employeesList}">
				<option value="${emp.id}">${emp.name}</option>
			</c:forEach>
		</select>

		<!-- 編集する日を選択 -->
		<label>編集する日付:</label>
		 <input type="date" id="update_date" name="day"value="${today}" />

		<!-- 更新削除へ行くボタン -->
		<button type="submit" name="shift_updatebutton" value="update">編集へ</button>
	</form>



	<div id="shift_list">
		<!-- シフト一覧 -->
		<!-- シフト一覧表示テーブル -->
		<div id="shift_container">

			<table class="weekly-shift-table">

				<!-- ヘッダー（日付を横に並べる） -->
				<thead>
					<tr>
						<th class="emp-header">従業員</th>
						<c:forEach var="displayDate" items="${displayDateList}"
							varStatus="status">
							<th class="date-header-cell" data-index="${status.index}">${displayDate}</th>
						</c:forEach>
					</tr>
				</thead>

				<!-- 行：左端に従業員名を配置 -->
				<tbody>
					<c:forEach var="emp" items="${employeesList}">
						<tr data-emp-id="${emp.id}">
							<td class="emp-name-cell">${emp.name}</td>

							<!--  列：右に向かって2週間分のシフトを配置 -->
							<c:forEach var="date" items="${dateList}">
								<td class="shift-cell"><c:choose>
										<c:when test="${empty calendarMap[date][emp.id]}">
											<span class="s-none">未登録</span>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${calendarMap[date][emp.id].intime == 0}">
													<span class="s-0">早朝</span>
												</c:when>
												<c:when test="${calendarMap[date][emp.id].intime == 1}">
													<span class="s-1">朝</span>
												</c:when>
												<c:when test="${calendarMap[date][emp.id].intime == 2}">
													<span class="s-2">昼</span>
												</c:when>
												<c:when test="${calendarMap[date][emp.id].intime == 3}">
													<span class="s-3">夕</span>
												</c:when>
												<c:when test="${calendarMap[date][emp.id].intime == 4}">
													<span class="s-4">休</span>
												</c:when>
											</c:choose>
										</c:otherwise>
									</c:choose></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>
		<br>
	</div>

	<br>
	<!--登録  -->
	<form action="ShiftDisplayServlet" method="post">
		<!-- 登録者名選択 -->
		<!-- 初めに空文字をoptionに入れる -->
		<div>シフト登録</div>
		<select id="submit_employee" name="id">
			<option value="">従業員を選択してください</option>
			<c:forEach var="emp" items="${employeesList}">
				<option value="${emp.id}">${emp.name}</option>
			</c:forEach>
		</select>

		<!-- シフト時間選択 -->
		<select id="submit_date" name="intime">
			<option value="0">早朝</option>
			<option value="1">朝</option>
			<option value="2">昼</option>
			<option value="3">夕</option>
			<option value="4">休</option>
		</select>
		<!-- 日付選択 -->
		<input type="date" name="day"value="${today}">
		<!-- 登録ぼたん -->
		<button type="submit" name="shift_submitbutton" value="シフト登録">登録</button>
	</form>
	
	<!-- シフト見本 -->
	<div id="shift_sample">
		<ul>
			<li>早朝：5：00～9：00</li>
			<li>朝：9：00～12：00</li>
			<li>昼：12：00～15：00</li>
			<li>夕：15：00～19：30</li>
			<li>休：休み</li>
		</ul>
	</div>
	<br>


	<!-- JSの読み込み -->
	<script src="ShiftDisplay.js"></script>
</body>
</html>