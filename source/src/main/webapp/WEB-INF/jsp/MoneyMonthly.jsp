<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支表示</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
<%@ include file="Common.jsp" %>
</head>
<body>

<!-- タイトル左上 -->
<h1>収支表示</h1>

	<!-- 収支を表示する年月の選択 -->
	<form action="MoneyMonthlyServlet" method="POST" id="fr">
		<!-- onchange（JS）により年月を切り替えた時に処理が発生する→JSへ -->
		<select id ="date" onchange = "datechanged()">
			<option value="">年月の選択</option>
			<option value="1">2024年1月</option>
			<option value="2">2026年4月</option>
			<option value="3">2026年5月</option>
			<option value="4">2026年6月</option>
			<option value="5">2026年7月</option>
		</select>
	</form>
<!-- 円グラフのスタイル -->
<canvas id="money" width="250" height="250"></canvas>
<canvas id="income" width="250" height="250"></canvas>
<canvas id="expence" width="250" height="250"></canvas>
</body>

<script>
// onchangeの処理  form.submit()によりselectが変わるごとにformが送られservletが呼び出される 
function datechanged(){
	let selected = document.getElementById("date").value;
	let form = document.getElementById("fr");
	form.submit();
}
 

//顔文字のドーナツチャートの部分の処理
let inputData =${moneyList};
let context = document.querySelector("#money").getContext('2d')
new Chart(context, {
  type: 'doughnut',
  data: {
    labels: ["収入", "支出"],
    datasets: [{
      //ここで取得した配列の中身を分解して配置する
      data: [inputData[0], inputData[1],],
      backgroundColor: ['#2E86DE','#FF4D4D',],
    }]
  },
  options: {
    responsive: false,
  }
});

let inputData2 =${incomeList};
//二つ以上グラフを書くときはcontext2,3,4...となんでもいいので名前を変更する
let context2 = document.querySelector("#income").getContext('2d')
//上記を変更した場合は、この下の名前（context）も上の名前に合わせる
new Chart(context2, {
type: 'pie',
data: {
	//収入の項目(8)
  labels: ["生乳販売", "子牛販売", "廃用牛販売", "補助金・交付金", "堆肥販売","加工品","観光","その他"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData2[0], inputData2[1],inputData2[2],inputData2[3],inputData2[4],inputData2[5],inputData2[6],inputData2[7],],
    backgroundColor: ['#E66767','#F5CD79','#574B90','#78E08F','#38ADA9','#1E3799','#82CCDD','#60A3BC',],
  }]
},
options: {
  responsive: false,
}
});

let inputData3 =${expenceList};
//二つ以上グラフを書くときはcontext2,3,4...となんでもいいので名前を変更する
let context3 = document.querySelector("#expence").getContext('2d')
//上記を変更した場合は、この下の名前（context）も上の名前に合わせる
new Chart(context3, {
type: 'pie',
data: {
	//収支の項目（11）
  labels: ["飼料費・敷料費", "光熱水料動力費", "その他諸材料費", "種付・獣医飼料・医薬品費",
	  "貸借料及び料金","物件税及び公課諸負担","乳牛償却費","建物費","自動車・農具費","労働費","その他"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData3[0], inputData3[1],inputData3[2],inputData3[3],inputData3[4],
    	   inputData3[5], inputData3[6],inputData3[7],inputData3[8],inputData3[9],inputData3[10],],
    backgroundColor: ['#FF4D4D','#FF944D','#FFD14D','#E1FF4D','#8CFF4D','#4DFF88',
    					'#4DFFFF','#4DA6FF','#4D4DFF','#944DFF','#FF4DFF',],
  }]
},
options: {
  responsive: false,
}
});




</script>


</html>
