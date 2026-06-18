<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員更新・削除</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/EmployyesUpdateDeleteServlet" method="post">	
	
<div class ="employees-card">
	<c:forEach var="UdList" items="${empUdList}">
		<input type="hidden" name="id" value="${UdList.id}">
		<input type="text" name="name" value=<c:out value="${UdList.name}"/>><br>
		<input type="text" name="age" value=<c:out value="${UdList.age}"/>><br>
		<input type="text" name="gender" value=<c:out value="${UdList.strGender}"/>><br>
		<input type="text" name="phone" value=<c:out value="${UdList.phone}"/>><br>
		<input type="text" name="address" value=<c:out value="${UdList.address}"/>><br>
		<input type="text" name="login_id" value=<c:out value="${UdList.login_id}"/>><br>
		<input type="text" name="password" value=<c:out value="${UdList.password}"/>><br>
		<button type="submit" name="edit" value="update">変更</button><br>
		<button type="submit" name="delete" value="Delete">削除</button><br>
	</c:forEach>

<!--  
	<ul>
		<li>氏名 <input type="text" name="name"></li>
		<li>性別 
			<label><input type="radio" name="gender" value="男性" checked>男性</label>
			<label><input type="radio" name="gender" value="女性">女性</label>
			<label><input type="radio" name="gender" value="どちらでもない">どちらでもない</label>
		</li>
		<li>年齢 <input type="text" name="age"></li>
		<li>住所 <input type="text" name="address"></li>
		<li>電話番号 <input type="text" name="phone"></li>
	</ul>
-->
</div>

</form>

<script src="${pageContext.request.contextPath}/js/EmployeesUpdateDelete.js"></script>

</body>
</html>