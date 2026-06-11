<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員リスト</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Employyes.css"><!-- CSS読み込み -->
</head>
<body>

<h1>従業員リスト</h1>
<div class = "employees-card">
<ul>
	<li>従業員id</li>
	<li>名前</li>
	<li>年齢</li>
	<li>性別</li>
	<li>電話番号</li>
	<li>住所</li>
</ul>
	<button type ="submit" name ="edit" value="編集">編集</button>
</div>

<img src="photo.jpg" alt="牛と人間">	<!-- 牛のロゴ まだ何もない-->
</body>
</html>