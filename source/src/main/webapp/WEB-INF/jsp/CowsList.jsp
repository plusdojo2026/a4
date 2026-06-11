<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- 見本の表 -->
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

<!-- サーブレットから受け取ったcowListの数だけ繰り返し処理 -->
<c:forEach var="cow" items="${cowList}">
  <div class="cow-card">
    <ul>
      <!--EL式を使って、ウシ1頭ずつのデータを表示 -->
      <li>個体ID: ${cow.id}</li>
      <li>名前: ${cow.name}</li>
      <li>性別: ${cow.gender}</li>
      <li>生年月日: ${cow.birth_day}</li>
      <li>生死: ${cow.status}</li>
    </ul>
    
    <!-- 編集ボタン。どのウシを編集するか分かるように、valueにIDを仕込む -->
    <button type="submit" name="edit" value="${cow.id}">編集</button>
  </div>
</c:forEach>


<!-- JSの読み込み -->
<script src="CowList.js"></script>
</body>
</html>