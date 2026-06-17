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
                 <option value = "${emp.id}">${emp.name}</option>
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
        <option value="0">早朝</option>
        <option value="1">朝</option>
        <option value="2">昼</option>
        <option value="3">夕</option>
        <option value="4">休</option>
    </select>
    <!-- 日付選択 -->
    <input type="date" name="day">
    <!-- 登録ぼたん -->
    <button type="submit" name="shift_submitbutton" value="シフト登録">登録</button>
 </form> 
       
<div id="shift_list"><!-- シフト一覧 -->
     <!-- シフト一覧表示テーブル -->
    <div id="shift_container">
    
        <table border="1">
        
        <!-- ヘッダー（日付） -->
         <tr>
                <th>従業員</th>
                
            <c:forEach var="date" items="${calendarMap.keySet()}">
            <th>${date}</th>
            </c:forEach>
            
         </tr>
         
        <!-- 行：従業員 -->
             <c:forEach var="emp" items="${employeesList}">
             <tr>
              <td>${emp.name}</td>
             
              <!-- 列：日付 -->
              <c:forEach var="date" items="${calendarMap.keySet()}">
             <td>
             
               <c:choose>
                  <c:when test="${empty calendarMap[date][emp.id]}">未登録</c:when>

                   <c:otherwise><!--nullじゃないなら～ -->
                   <c:choose>
                   <c:when test="${calendarMap[date][emp.id].intime == 0}">早朝</c:when>
                   <c:when test="${calendarMap[date][emp.id].intime == 1}">朝</c:when>
                   <c:when test="${calendarMap[date][emp.id].intime == 2}">昼</c:when>
                   <c:when test="${calendarMap[date][emp.id].intime == 3}">夕</c:when>
                   <c:when test="${calendarMap[date][emp.id].intime == 4}">休</c:when>
                   </c:choose>
                   </c:otherwise>
                </c:choose>

             </td>
             </c:forEach>
             </tr>
            </c:forEach>
            
          
        </table>
        
     </div><br>
</div><br>
    
    <!-- シフト見本 -->
    <div id="shift_sample">
        <ul>
	        <li>早朝：5：00～9：00</li>
	        <li>朝：9：00～12：00</li>
	        <li>昼：12：00～15：00</li>
	        <li>夕：15：00～19：30</li>
	        <li>休：休み</li>
       </ul>
    </div><br> 
    <div id="errorArea">${errorMsg}</div>
<div>${msg}</div>	<!-- メッセージ表示欄 -->

    <!-- JSの読み込み -->
<script src="ShiftDisplay.js"></script>
</body>
</html>