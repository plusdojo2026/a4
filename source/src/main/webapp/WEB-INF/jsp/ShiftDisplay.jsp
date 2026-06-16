<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>シフト更新画面</title>
</head>
<%@ include file="Common.jsp" %>
 <body>
<h1>シフト一覧・登録画面</h1>

<form action="ShiftDisplayServlet"method="post">
    <!-- 更新者名選択 -->
    <select id="update_employee" name="id">
            <option value="" >従業員を選択してください</option>        
            <c:forEach var="emp" items="${employeesList}">
                 <option value="${emp.id}">${emp.name}</option>
            </c:forEach> 
        </select>
    
    <!-- 編集する日を選択 -->
    <label>編集する日付:</label>
        <input
              type="date"
              id="update_date"
               name="day"
              value="2026-01-01"
              min="1980-01-01"
              max="2200-12-31" 
         />
    
    <!-- 更新削除へ行くぼたんボタン -->
 <button type="submit" name="shift_updatebutton" value="update">編集へ</button> 
 </form>
 
<form action="ShiftDisplayServlet" method="post">    <!-- 登録者名選択 -->
    <!-- 初めに空文字をoptionに入れる -->
     <select id="submit_employee" name="id"> 
            <option value="">従業員を選択してください</option>
            <c:forEach var="emp" items="${employeesList}">
                  <option value="${emp.id}">${emp.name}</option>
            </c:forEach> 
        </select>
    
    <!-- シフト時間選択 -->
    <select id="submit_date"name="intime"> 
        <option value="">時間を選択してください</option> 
        <option value="早朝">朝</option>
        <option value="朝">朝</option>
        <option value="昼">夕</option>
        <option value="夕">全</option>
        <option value="休">休</option>
    </select>
    <!-- 登録ぼたん -->
    <button type="submit" name="shift_submitbutton" value="シフト登録">登録</button>
 </form>       
    <div id="shift_list"><!-- シフト一覧 -->
    <table>
            <tr>
                <th>従業員名</th>
                <th>勤務時間</th>
            </tr>
    </table>
    </div><br>
    <!-- シフト見本 -->
    <div id="shift_sample">
        <ul>
	        <li>早朝：5：00～9：00</li>
	        <li>朝：9：00～12：00</li>
	        <li>昼：12：00～15：00</li>
	        <li>全：15：00～19：30</li>
	        <li>休：休み</li>
       </ul>
    </div><br> 
    <div id="errorArea"></div><br>	<!-- エラーメッセージ表示欄 -->

    <!-- JSの読み込み -->
<script src="ShiftDisplay.js"></script>
</body>
</html>