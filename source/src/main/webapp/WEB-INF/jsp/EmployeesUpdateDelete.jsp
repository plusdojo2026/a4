<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員更新・削除</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeesUpdateDelete.css">
</head>
<body>
<%@ include file="Common.jsp" %>
<form action="${pageContext.request.contextPath}/EmployeesUpdateDeleteServlet" id="myform" method="post">	
	
<div class ="employees-card">
	<c:forEach var="UdList" items="${empUdList}">
		<input type="hidden" name="id" value="${UdList.id}">
		<input type="hidden" name="admin" value="${UdList.admin} ">
		氏名:<input type="text" name="name" id="name" value=<c:out value="${UdList.name}"/>><br>
		年齢:<input type="text" name="age" id="age"pattern="[0-9]*" required value=<c:out value="${UdList.age}"/>><br>
		性別<label><input type="radio" name="strGender" value="男性" checked>男性</label>
		<label><input type="radio" name="strGender" value="女性">女性</label>
		<label><input type="radio" name="strGender" value="どちらでもない">どちらでもない</label><br>
		電話番号:<input type="text" name="phone" id="phone" value=<c:out value="${UdList.phone}"/>><br>
		住所:<input type="text" name="address" id="address" value=<c:out value="${UdList.address}"/>><br>
		ログインID:<input type="text" name="login_id" value=<c:out value="${UdList.login_id}" />><br>
		パスワード:<input type="text" name="password" value=<c:out value="${UdList.password}" />><br>
		<div id="error1"></div><br>
		<div id="error2"></div><br>
		<button type="submit" name="submit" value="update">変更</button><br>
		<button type="submit" name="submit" value="Delete">削除</button><br>
		
	</c:forEach>

</div>

</form>

<script src="${pageContext.request.contextPath}/js/EmployeesUpdateDelete.js"></script>

</body>
</html>