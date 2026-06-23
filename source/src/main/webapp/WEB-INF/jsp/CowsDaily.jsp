<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath}/js/CowsDaily.js" defer></script>
<meta charset="UTF-8">
<title>日別データ登録</title>
<%@ include file="Common.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/CowsDaily.css">




</head>

<body>
<!-- 日にちを表示 -->
	<p id="time" class="time"></p>
	
	<div class="layout-wrapper">
		<!-- 検索フォーム -->
	<form method="POST" action="/a4/CowsSearchServlet" class="search-area">
<h2>検索</h2>
	<label for="id">牛のID</label><br>
		<select id="id" name="cowId">
		<option value=""></option>
    	<c:forEach var="id" items="${idList}">
       	 <option value="${id}">${id}</option>
   	 	</c:forEach>
		</select><br>
	
	牛の名前<br>
	<input type="text" name="name"><br>
	生年月日<br>
	<input type="date" name="birthday"><br>
	<input type="submit" name="search" value="検索"><br>
	</form>	<!-- 検索フォームここまで --><br>
	


	
	<!-- タブ -->
	<div class="cowstab">
		<input id="tab_daily" type="radio" name="tab_btn" checked>  <!-- 日別データ入力 -->
		<input id="tab_monthly" type="radio" name="tab_btn">		<!-- 月別データ入力 -->
	
		<div class="tab_area">
			<label class="tab_daily_label" for="tab_daily">日別データ入力</label>  <!-- forで繋がるidを指定 -->
			<label class="tab_monthly_label" for="tab_monthly">月別データ入力</label>
		</div>
	
		<div class="panel_area">
			<div id="panel_daily" class="tab_panel"> <!--日別のパネル -->
			
			
				<form method="POST" action="/a4/CowsDailyServlet" id="form">
				<div class="form-row">
					<p >登録ID: ${id}</p><!-- 牛のIDを表示 -->
					<input type="hidden" name="id" value="${id}" required>
					</div>
					<div class="form-row">
					<label>日付</label>
					<input type="date" name="day" required><br><!-- 日付入力 -->
					</div>
					<div class="form-row">
					<label>体温</label>
					<input type="text"  name="temperature"><br><!-- 体温入力 -->
					</div>
					<div class="form-row">
					<label for="appetite">食欲</label><!-- 食欲選択 -->
					<select id="appetite" name="appetite" required>
						<option value=""></option>
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					</div>
					<div class="form-row">
				<label for="drinking">飲水量</label><!-- 飲水量 -->
					<select id="drinking" name="drinking" required>
						<option value=""></option>
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					</div>
					<div class="form-row">
					<label for="manure">排せつ物</label><!-- 排せつ物選択 -->
					<select id="manure" name="manure" required>
						<option value=""></option>
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					</div>
					<div class="form-row">
					<label for="health">健康状態</label><!-- 健康状態選択 -->
					<select id="health" name="health" required>
						<option value=""></option>
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					</div>
					<button type="submit" id="button" disabled>登録</button><!-- 登録ボタン -->
					</form><br>
					<p>${message}</p>
					
			</div>
			<div id="panel_monthly" class="tab_panel"> <!-- 月別のパネル -->
				<form method="POST" action="/a4/CowsMonthlyServlet" id="form2">
					<p>登録ID: ${id}</p><!-- 牛のIDを表示 -->
					<input type="hidden" name="id" value="${id}" required>
					日付<input type="date" name="day" required><br><!-- 日付 -->
					体重（Kg）<input type="text"  name="weight" required><br><!-- 体重入力 -->
					<label for="milkquality">牛乳の質</label><!-- 牛乳の質 -->
					<select id="milkquality" name="milkquality" required>
						<option value=""></option>
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select><br>
					細菌数<input type="text"  name="bacterial_count" required><br><!-- 細胞数入力 -->
					乳脂肪分(％)<input type="text"  name="milk_fat_content" required><br><!-- 乳脂肪入力 -->
					体細胞数<input type="text"  name="somatic_cell_count" required><br><!-- 体細胞数入力 -->
					<button type="submit" id="button2" disabled>登録</button><!-- 登録ボタン --><br>
					<p>${message}</p>
				</form>
				</div>
			</div>
		</div>
	</div>
		
</body>

</html>