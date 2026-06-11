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
<body>
	<!-- ページタイトル左上 -->
	<h1>ウシ個体の登録画面</h1>
	
	<!-- 画面左側 -->>
	<h3>新しいウシ！　おめでとう</h3>
	<img src="cow1.jpg" alt="牛のイラスト">	<!-- ウシのイラスト-->
	<!-- 画面右上側 -->	
	<table>フォームの例</table>
	
	<!-- 画面右下側 -->>
	<form>
		<table>
			<tr>
			<td>acha</td>
			</tr>
			<tr>
			<button>ファイル読み込み</button>
			</tr>
		</table>
		<button>ぽちっと送信</button>
	</form>
<!-- JSの読み込み -->
<script src="CowRegist.js"></script>
</body>
</html>