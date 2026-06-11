<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CowsUpdateDelete.css">
</head>
<body>
	<h1>牛のデータ更新</h1><!-- ページタイトル -->
	<!-- ページ左側牛の写真 -->
	<img src="cow1.jpg" alt="牛のイラスト">	<!-- ウシのイラスト-->
	<form method="POST" action="/a4/CowsUpdateDeleteServlet">
	<label>牛ID</label>
	<input type="text" name="cowId" value="${cow.cowId}" ><br>
	<input type="text" name="name" value="${cow.cowName}" ><br>
	<select name="gender" >
		<option value="オス">オス</option>
		<option value="">メス</option>
	</select>
	<input type="date" name="birthday" value="${cow.cowBirthday}" >
	<select name="status" >
	<option value="生">生</option>
	<option value="死">死</option>
	<option value="出荷済">出荷済</option>
	</select>
	<input type="submit" name="submit" value="更新">
	<input type="submit" name="submit" value="削除">
	</form>
</body>
</html>