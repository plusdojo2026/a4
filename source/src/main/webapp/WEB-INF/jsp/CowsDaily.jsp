<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日別データ登録</title>
<!-- タブのスタイル↓ -->
<style>  
	.cowstab_wrap{width:500px; margin:80px auto;}
	input[type="radio"]{display:none;}
	.tab_area{font-size:0; margin:0 10px;}
	.tab_area label{width:150px; margin:0 5px; display:inline-block; padding:12px 0; color:#999; background:#ddd; text-align:center; font-size:13px; cursor:pointer; transition:ease 0.2s opacity;}
	.tab_area label:hover{opacity:0.5;}
	.panel_area{background:#fff;}
	.tab_panel{width:100%; padding:80px 0; display:none;}
	.tab_panel p{font-size:14px; letter-spacing:1px; text-align:center;}

	#tab_daily:checked ~ .tab_area .tab_daily_label{background:#fff; color:#000;}
	#tab_daily:checked ~ .panel_area #panel_daily{display:block;}
	#tab_monthly:checked ~ .tab_area .tab_monthly_label{background:#fff; color:#000;}
	#tab_monthly:checked ~ .panel_area #panel_monthly{display:block;}
</style>

</head>
<body>
	<h2>検索</h2>	<!-- 検索フォーム -->
	<form method="POST" action="/a4/CowsDailyServlet">
	牛のID
	<label for="id">牛のID</label>
		<select id="id" name="cowId">
    	<c:forEach var="id" items="${idList}">
        <option value="${id}">${id}</option>
    </c:forEach>
</select>
	
	牛の名前<br>
	<input type="text" name="name"><br>
	生年月日<br>
	<input type="date" name="birthday"><br>
	<input type="submit" name="search" value="検索"><br>
	</form>	<!-- 検索フォームここまで -->
	
	<div class="cowstab">
		<input id="tab_daily" type="radio" name="tab_btn" checked>  <!-- 日別データ入力 -->
		<input id="tab_monthly" type="radio" name="tab_btn">		<!-- 月別データ入力 -->
	
		<div class="tab_area">
			<label class="tab_daily_label" for="tab_daily">日別データ入力</label>  <!-- forで繋がるidを指定 -->
			<label class="tab_monthly_label" for="tab_monthly">月別データ入力</label>
		</div>
	
		<div class="panel_area">
			<div id="panel_daily" class="tab_panel"> <!--日別のパネル -->
				<form method="POST" action="/a4/CowsDailyServlet">
					<p>ID: ${id}</p><!-- 牛のIDを表示 -->
					
					体温<input type="text"  name="temperature"><br><!-- 体温入力 -->
					
					<label for="appetite">食欲</label><!-- 食欲選択 -->
					<select id="appetite" name="appetite">
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					
				<label for="drinking">飲水量</label><!-- 飲水量 -->
					<select id="drinking" name="drinking">
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					
					<label for="manure">排せつ物</label><!-- 排せつ物選択 -->
					<select id="manure" name="manure">
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					
					<label for="health">健康状態</label><!-- 健康状態選択 -->
					<select id="health" name="health">
						<option value="〇">〇</option>
						<option value="△">△</option>
						<option value="✕">✕</option>
					</select>
					
					<button type="submit">登録</button><!-- 登録ボタン -->
					</form>
			</div>
			<div id="panel_monthlu" class="tab_panel"> <!-- 月別のパネル -->
				<form method="POST" action="/a4/CowsMonthlyServlet">
					<p>ID: ${id}</p><!-- 牛のIDを表示 -->
					体重<input type="text"  name="weight"><br><!-- 体重入力 -->
					細菌数<input type="text"  name="bacterial_count"><br><!-- 細胞数入力 -->
					乳脂肪分<input type="text"  name="milk_fat_content"><br><!-- 乳脂肪入力 -->
					体細胞数<input type="text"  name="somatic_cell_count"><br><!-- 体細胞数入力 -->
					<button type="submit">登録</button><!-- 登録ボタン -->
				</form>
			</div>
		</div>
	</div>	
</body>
</html>