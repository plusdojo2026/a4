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
<form action="${pageContext.request.contextPath}/EmployeesRegistServlet" method="post">	
	
<div class ="employees-card">
	<ul>
		<li>氏名 <input type="text" name="name"></li>
		<!-- 性別は選択式 -->
		<li>性別 
			<label><input type="radio" name="gender" value="男性" checked>男性</label>
			<label><input type="radio" name="gender" value="女性">女性</label>
			<label><input type="radio" name="gender" value="どちらでもない">どちらでもない</label>
		</li>
		<li>年齢 <input type="text" name="age"></li>
		<li>住所 <input type="text" name="address"></li>
		<li>電話番号 <input type="text" name="phone"></li>
	</ul>
	<input type="hidden" name="admin" value="1">
	
	
	<input type="reset" value="リセット">
	<input type="submit" name="regist" value="登録">
</div>
</form>
</body>
</html>