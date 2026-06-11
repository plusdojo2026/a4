<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>シフト更新画面</title>
</head>
<body>
<h1>シフト更新画面</h1>

    <!-- 更新者名選択 -->
    <select id="update_employee">
        <!-- 初めに空文字をoptionに入れる -->
        <option value="" >
                従業員を選択してください
        </option>        
         <!-- リストから1件ずつ「emp」変数名で取り出す -->
         <!-- <option> タグの value 属性に「従業員ID」、画面上の表示名に「従業員名」を設定 -->
              <c:forEach var="emp" items="${employeeList}">
                     <option value="${emp.id}">
                     ${emp.name}
                     </option>
              </c:forEach> 
    </select>
    
    <!-- 編集する日を選択 -->
    <label>編集する日付:</label>
        <input
              type="date"
              id="update_date"
              name="trip-start"
              value="2026-01-01"
              min="1980-01-01"
              max="2200-12-31" 
         />
    
    <!-- 更新削除へ行くぼたんボタン -->
    <button type="button" name="shift_updatebutton">編集へ</button>    
    
    <!-- 登録者名選択 -->
    <!-- 初めに空文字をoptionに入れる -->
    <select id="submit_employee"> 
        <option value="">
                従業員を選択してください
        </option>
         <!-- リストから1件ずつ「emp」変数名で取り出す -->
         <!-- <option> タグの value 属性に「従業員ID」、画面上の表示名に「従業員名」を設定 -->
         <c:forEach var="emp" items="${employeeList}">
              <option value="${emp.id}">
                      ${emp.name}
              </option>
         </c:forEach> 
    </select>
    
    <!-- シフト時間選択 -->
    <select id="submit_date"> 
        <option value="">時間を選択してください</option> 
        <option value="朝">朝</option>
        <option value="夕">夕</option>
        <option value="全">全</option>
        <option value="休">休</option>
    </select>
    <!-- 登録ぼたん -->
    <button type="submit" name="shift_submitbutton" value="シフト登録">登録</button>
        
    <div id="shift_list"><!-- シフト一覧 -->
    <table>
            <tr>
                <th>従業員名</th>
                <th>勤務時間</th>
            </tr>
    </table>
    </div><br>
    <div id="shift_sample"></div><br> <!-- シフト見本 -->
    <div id="errorArea"></div><br>	<!-- エラーメッセージ表示欄 -->

</body>
</html>