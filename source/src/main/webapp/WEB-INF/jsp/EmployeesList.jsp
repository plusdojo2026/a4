<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員リスト</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployyesList.css"><!-- CSS読み込み -->
</head>
<body>

<h1>従業員リスト</h1>
<div class = "employees-card">
<ul>
	<li>従業員id</li>
	<li>名前</li>
	<li>性別</li>
	<li>電話番号</li>
	<li>住所</li>
</ul>
	<button type ="button" name ="edit" value="編集">編集</button>
</div>

<c:forEach var="list" items="${empList}">
				<form action="EmployeesUpdateDeleteServlet" method="POST">
				<c:out value="${list.id}"/><br>
				<c:out value="${list.name}"/><br>
				<c:out value="${list.strGender}"/><br>
				<c:out value="${list.phone}"/><br>
				<c:out value="${list.address}"/><br>
				<button type="submit" name="edit" value="updateDelete">編集</button><br>
				</form>
			</c:forEach>

<img src="chichishibori.png" alt="牛と人間">	<!-- 牛のロゴ まだ何もない-->
</body>
</html>