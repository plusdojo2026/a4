<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>シフト更新画面</title>
</head>
<body>

    <select name="update_employee"> 
        <option value="">従業員を選択してください</option><%-- 初めに空文字をoptionに入れる --%>
         <%-- リストから1件ずつ「emp」変数名で取り出す --%>
        <c:forEach var="emp" items="${employeeList}">
        <!-- <option> タグの value 属性に「従業員ID」、画面上の表示名に「従業員名」を設定 -->
        <option value="${emp.id}">${emp.name}</option>
        </c:forEach> 
    </select>
    
    <select name="date"> 
        <option value="">日付を選択してください</option> 
    </select>
    
    <button type="button" name="shift_updatebutton">更新</button>    
    
    <select name="submit_employee"> 
        <option value="">従業員を選択してください</option><%-- 初めに空文字をoptionに入れる --%>
         <%-- リストから1件ずつ「emp」変数名で取り出す --%>
        <c:forEach var="emp" items="${employeeList}">
        <!-- <option> タグの value 属性に「従業員ID」、画面上の表示名に「従業員名」を設定 -->
        <option value="${emp.id}">${emp.name}</option>
        </c:forEach> 
    </select>
    
    <select name="date"> 
        <option value="">日付を選択してください</option> 
    </select>
    <button type="submit" name="shift_submitbutton" value="シフト登録">登録</button>
        
    <div id="shift_list"></div><br> <!-- シフト一覧 -->
    <div id="shift_sample"></div><br> <!-- シフト見本 -->
    <div id="errorArea"></div><br>	<!-- エラーメッセージ表示欄 -->

</body>
</html>