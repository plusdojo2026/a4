<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeesRegist.css"><!-- CSS読み込み -->
</head>
<%@ include file="Common.jsp" %>
<body>
<div class="title-container">
	<h1>🥛 従業員登録</h1>
</div>
<form action="${pageContext.request.contextPath}/EmployeesRegistServlet" id="myForm"method="post">	
	
<div class ="employees-card">
	<ul>
		<li>氏名 <input type="text" name="name" id="name" ></li>
		<!-- 性別は選択式 -->
		<li>性別 
			<label><input type="radio" name="gender" value="男性" checked>男性</label>
			<label><input type="radio" name="gender" value="女性">女性</label>
			<label><input type="radio" name="gender" value="どちらでもない">どちらでもない</label>
		</li>
		<li>年齢 <input type="text" name="age" id="age" pattern="[0-9]*"></li>
		<li>住所 <input type="text" name="address" id="address" ></li>
		<li>電話番号 <input type="text" name="phone" id="phone" pattern="\d{2,4}-\d{3,4}-\d{3,4}">></li>
	</ul>
	<input type="hidden" name="admin" value="1">
	<input type="hidden" name="login_id" value="id">
	<input type="hidden" name="password" value="pass">

	
	<div id="error1"></div><br>
	<div id="error2"></div><br>
	<input type="reset" value="リセット">
	<input type="submit" id="submit"name="regist" value="登録">
</div>
</form>
<script src="${pageContext.request.contextPath}/js/EmployeesRegist.js"></script>
</body>
</html>