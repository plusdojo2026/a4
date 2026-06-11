<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>シフト更新・削除画面</h1>
   <form action="/a4/ShiftUpdateServlet" method="post" id="form">
		<div>
			  <p>従業員名</p>  
	          <p>変更する日付</p> 
	       <p>シフトの時間: 
				<select name="shiftTime">
					<option value="朝">朝</option>
					<option value="夕">夕</option>
					<option value="全">全</option>
					<option value="休">休</option>
				</select>
			</p>
	    </div>
	     <button type="submit">更新する</button>
	    <a href="${ShiftUpdateDelete.backTo}">シフト一覧・登録へ戻る</a>
	</form>
   
   <!-- JSの読み込み -->
<script src="ShiftUpdateDelete.js"></script>
</body>
</html>