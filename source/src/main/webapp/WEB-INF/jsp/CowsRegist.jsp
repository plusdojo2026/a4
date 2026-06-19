<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ウシ個体登録画面</title>
<!-- CSS読み込み -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CowRegist.css">
</head>
<%@ include file="Common.jsp" %>
<body>
	<!-- ページタイトル左上 -->
	<h1>ウシ個体の登録画面</h1>
	
	<!-- 画面左側 -->>
	<h3>新しいウシ！　おめでとう</h3>
	<img src="cow1.jpg" alt="牛のイラスト">	<!-- ウシのイラスト-->
	<!-- 画面右上側 -->	
	<table class="mihon-table">
		<thead>
			<tr>
				<th>ウシID(10ケタ)</th>
		      	<th>名前</th>
		      	<th>性別</th>
		      	<th>生年月日</th>
		      	<th>生死(自動)</th>
		      	<th>写真</th>
		   	</tr>
		 </thead>
		 <tbody>
		  	<tr>
		     	<td>1000000000</td>
		     	<td>ウシタロウ</td>
		     	<td>メス</td>
		     	<td>2026/06/11</td>
		     	<td>生</td>
		     	<td>ファイル名</td>
		   	</tr>
		</tbody>
	</table>
	
	<!-- 画面右下側 -->>
	<form id="cow_regist_form" method="POST" action="CowRegistServlet">
  <table class="cow-regist-table" border="1" style="border-collapse: collapse; text-align: center;">
    <thead>
      <tr>
        <th>ウシID</th>
        <th>名前</th>
        <th>性別</th>
        <th>生年月日</th>
        <th>生死(自動)</th>
        <th>写真</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><input type="text" name="id" required></td>
        <td><input type="text" name="name" required></td>
        <td><input type="text" name="gender" required></td>
        <td><select name="gender">
				<option value="オス">オス</option>
				<option value="メス">メス</option>
			</select><td>
        <td><input type="date" name="birthday"><br></td>
        <td><input type="text" name="status"></td>
        <td><input type="text" name="photo"></td>
      </tr>
    </tbody>
  </table>
  <button type="submit">登録</button>
</form>
<!-- JSの読み込み -->
<script src="CowRegist.js"></script>
</body>
</html>