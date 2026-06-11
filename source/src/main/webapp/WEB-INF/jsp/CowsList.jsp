<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ウシ一覧</title>
<!-- CSS読み込み -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CowList.css">
</head>
<body>

<h1>ウシ一覧リスト</h1>
<div class = "example-cow-card">
<ul>
	<li>個体ID</li>
	<li>名前</li>
	<li>性別</li>
	<li>生年月日</li>
	<li>生死</li>
</ul>
	<button type ="submit" name ="edit" value="編集">編集</button>
</div>

<div class = "cow-card">

</div>

<!-- JSの読み込み -->
<script src="CowList.js"></script>
</body>
</html>