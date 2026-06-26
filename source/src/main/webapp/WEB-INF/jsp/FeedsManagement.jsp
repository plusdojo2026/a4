<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エサの管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/FeedsManagement.css">
<!-- タブのCSS  -->
<style>
	.tab_wrap{width:500px; margin:80px auto;}
	input[type="radio"]{display:none;}
	.tab_area{font-size:0; margin:0 10px;}
	.tab_area label{width:150px; margin:0 5px; display:inline-block; padding:12px 0; color:#999; background:#ddd; text-align:center; font-size:13px; cursor:pointer; transition:ease 0.2s opacity;}
	.tab_area label:hover{opacity:0.5;}
	.panel_area{background:#fff;}
	.tab_panel{width:100%; padding:80px 0; display:none;}
	.tab_panel p{font-size:14px; letter-spacing:1px; text-align:center;}

	#tab1:checked ~ .tab_area .tab1_label{background:#fff; color:#000;}
	#tab1:checked ~ .panel_area #panel1{display:block;}
	#tab2:checked ~ .tab_area .tab2_label{background:#fff; color:#000;}
	#tab2:checked ~ .panel_area #panel2{display:block;}
	#tab3:checked ~ .tab_area .tab3_label{background:#fff; color:#000;}
	#tab3:checked ~ .panel_area #panel3{display:block;}

</style>
</head>
<%@ include file="Common.jsp" %>

<body>
<div class="title-container">
	<!-- 左上ページタイトルの表示  -->
		<h1>🐮 エサの管理</h1>
</div>
<div class=layout>			
	<!-- ここからタブ  -->
	<div class="tab_wrap">
		<input id="tab1" type="radio" name="tab_btn" checked>
		<input id="tab2" type="radio" name="tab_btn">
		
	
		<div class="tab_area">
			<label class="tab1_label" for="tab1">増量</label>
			<label class="tab2_label" for="tab2">減量</label>	
		</div>
		
			<div class="panel_area">
				<div id="panel1" class="tab_panel">
					<form action="/a4/FeedsManagementServlet" method="post" id="form">
						<input type="hidden" name="record"value="増えた量">
						<input type="number" name="money">
						<input type="submit" name="submit" value="更新"><br>
						エサが増えた時の処理
					</form>
				</div>
				<div id="panel2" class="tab_panel">
					<form action="/a4/FeedsManagementServlet" method="post" id="form">
						<input type="hidden" name="record"value="減った量">
						<input type="number" name="money">
						<input type="submit" name="submit" value="更新"><br>
						エサが減ったときの処理
					</form>
				</div>
				${msg }<br>
			</div>
	</div>
	
	<div class="amount">
		<div class=feeds>
			<h2>エサの総量</h2>
		</div>
		<div class=total>
			<h3> 総量： ${total}kg </h3>
		</div>
		<div>
			<imag class="picture" src="ushi_kataguruma.png" alt="">
		</div>
	</div>
</div>

<!-- JSの読み込み -->
<script src="${pageContext.request.contextPath}/js/FeedsManagement.js"></script>

</body>
</html>