<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支登録</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

</head>
<body>
	<!-- 左上にページタイトルの表示 -->
	<h1>収支の登録</h1>
	
	<!-- 登録フォーム　画面右側 -->
	<form method="post" action="/a4/MoneyRegistServlet">
		<label for="money">収入・支出の登録</label>
			<!-- コンボボックス -->
			<select class="parent" name="money">
				<option value="" selected="selected">選択</option>
				<option value="income">収入</option>
				<option value="expend">支出</option>
			</select><br>
			
		<label for="reason">理由の登録</label>
			<select class="children" name="reason" disabled>
				<option value="" selected="selected">項目の選択</option>
				<option value="生乳販売" data-val="income">生乳販売</option>
				<option value="子牛販売" data-val="income">子牛販売</option>
				<option value="廃用牛販売" data-val="income">廃用牛販売</option>
				<option value="補助金・交付金" data-val="income">補助金・交付金</option>
				<option value="堆肥販売" data-val="income">堆肥販売</option>
				<option value="加工品" data-val="income">加工品</option>
				<option value="観光" data-val="income">観光</option>
				<option value="飼料費・敷料費" data-val="expend">飼料費・敷料費</option>
				<option value="光熱水料動力費" data-val="expend">光熱水料動力費</option>
				<option value="その他諸材料費" data-val="expend">その他諸材料費</option>
				<option value="種付・獣医飼料・医薬品費" data-val="expend">種付・獣医飼料・医薬品費</option>
				<option value="貸借料及び料金" data-val="expend">貸借料及び料金</option>
				<option value="物件税及び公課諸負担" data-val="expend">物件税及び公課諸負担</option>
				<option value="乳牛償却費" data-val="expend">乳牛償却費</option>
				<option value="建物費" data-val="expend">建物費</option>
				<option value="自動車・農具費" data-val="expend">自動車・農具費</option>
				<option value="労働費" data-val="expend">労働費</option>
			</select><br>
			
			<!-- https://www.maff.go.jp/j/seisan/kankyo/hozen_type/h_sehi_kizyun/pdf/ibasiza19.pdf
			これに費用の項目を増やす -->
			
			
			金額<input type="text" name="amount"><br>
			
			<button type="submit">登録</button>
	
	</form>
	
<script src="MoneyRegist.js"></script>
</body>
</html>