<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<table class="cow-update-table" border="1" style="border-collapse: collapse; text-align: center;">
    <thead>
      <tr>
        <th>ウシID</th>
        <th>名前</th>
        <th>性別</th>
        <th>生年月日</th>
        <th>生死</th>
        <th>写真</th>
        <th>死亡日時</th>
        <th>死因</th>
        <th>登録日</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><input type="text" name="id" required></td>
        <td><input type="text" name="name" required></td>
        
        <td><select name="gender">
				<option value="1">オス</option>
				<option value="2">メス</option>
			</select></td>
			
        <td><input type="date" name="birth_day"></td>
        
        <td><select name="status">
       			<option value="生">生</option>
       			<option value="死">死亡</option>
       			<option value="出荷済">出荷済</option>
            </select></td>
            
        <td><input type="text" name="photo"></td>
        
        <td><input type="date" name="updatedate"></td>
        
        <td><select name="cause">
        		<option value="老衰">老衰</option>
        		<option value="病死">病死</option>
        		<option value="事故死">事故死</option>
        		<option value="死産">死産</option>
        		<option value="その他">その他</option>
        	</select></td>
        	
        	<td><input type ="date" name ="regist_day"></td>
      </tr>
    	</tbody>
  		</table>
  		
  		<button type="submit" name="submit" value="更新">変更</button>
  		<button type="submit" name="submit" value="削除">削除</button>
	</form>
	<!-- JSの読み込み -->
	<script src="CowsUpdateDelete.js"></script>
	
</body>
</html>