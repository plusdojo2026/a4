<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エサの管理</title>
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
<body>
	<div class="tab_wrap">
	<input id="tab1" type="radio" name="tab_btn" checked>
	<input id="tab2" type="radio" name="tab_btn">
	<input id="tab3" type="radio" name="tab_btn">

	<div class="tab_area">
		<label class="tab1_label" for="tab1">tab1</label>
		<label class="tab2_label" for="tab2">tab2</label>
		<label class="tab3_label" for="tab3">tab3</label>
	</div>
	<div class="panel_area">
		<div id="panel1" class="tab_panel">
			<p>panel1</p>
		</div>
		<div id="panel2" class="tab_panel">
			<p>panel2</p>
		</div>
		<div id="panel3" class="tab_panel">
			<p>panel3</p>
		</div>
	</div>
</div>

</body>
</html>