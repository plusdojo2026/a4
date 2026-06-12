<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支登録</title>


</head>
<body>
	<!-- 左上にページタイトルの表示 -->
	<h1>収支の登録</h1>
	
	<!-- 登録フォーム　画面右側 -->
	<form method="post" action="/a4/MoneyRegistServlet">
		<label for="money">収入・支出の登録</label>
			<select id="money" name="money">
				<option value="income">収入</option>
				<option value="expend">支出</option>
			</select><br>
			<label for="reason">理由の登録</label>
			<select id="reason" name="reason">
				<option value="income">収入</option>
				<option value="expend">支出</option>
			</select><br>
			
			<!-- https://www.maff.go.jp/j/seisan/kankyo/hozen_type/h_sehi_kizyun/pdf/ibasiza19.pdf
			これに費用の項目を増やす -->
			
			
			金額<input type="text" name="amount"><br>
			
			<button type="submit">登録</button>
	
	</form>
	
<script src="MoneyRegist.js"></script>
</body>
</html>