<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>収支表示</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
</head>
<body>

<h1>収支表示</h1>
	<form action="MoneyMonthlyServlet" method="POST">
		<select id ="date" onchange = "datechanged">
			<option value="">日付の選択</option>
			<option value="1">2024年1月</option>
			<option value="2">2024年4月</option>
			<option value="3">2026年6月</option>
			<option value="4">2026年7月</option>
		</select>
	</form>
<canvas id="money" width="250" height="250"></canvas>
<canvas id="income" width="250" height="250"></canvas>
<canvas id="expence" width="250" height="250"></canvas>
</body>

<script>
function datechanged(){
	let selected = document.getElementById("date").value;
	form.submit;
	//?
}
function 


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
      backgroundColor: ['#7fffd4', '#87cefa', ],
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
  labels: ["生乳販売", "子牛販売", "廃用牛販売", "補助金・交付金", "堆肥販売","加工品","観光"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData2[0], inputData2[1],inputData2[2],inputData2[3],inputData2[4],inputData2[5],],
    backgroundColor: ['#87cefa', '#7cfc00', '#d2691e','#dda0dd','#ffebcd','#dc143c','333333'],
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
  labels: ["飼料費・敷料費", "光熱水料動力費", "その他諸材料費", "種付・獣医飼料・医薬品費",
	  "貸借料及び料金","物件税及び公課諸負担","乳牛償却費","建物費","自動車・農具費","労働費"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData3[0], inputData3[1],inputData3[2],inputData3[3],inputData3[4],
    	   inputData3[5], inputData3[6],inputData3[7],inputData3[8],inputData3[9],],
    backgroundColor: ['#87cefa', '#7cfc00', '#d2691e','#dda0dd','#ffebcd'
    					,'#31143c','#dc1413','#11113c','#222222','#666666'],
  }]
},
options: {
  responsive: false,
}
});




</script>


</html>
