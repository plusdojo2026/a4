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
<canvas id="kimochi" width="600" height="500"></canvas>
<canvas id="kimo" width="250" height="250"></canvas>
</body>

<script>
//顔文字のドーナツチャートの部分の処理
let inputData =${moneyList};
let context = document.querySelector("#kimochi").getContext('2d')
new Chart(context, {
  type: 'doughnut',
  data: {
    labels: ["収入", "支出"],
    datasets: [{
      //ここで取得した配列の中身を分解して配置する
      data: [inputData[0], inputData[1],inputData[2],inputData[3],inputData[4],],
      backgroundColor: ['#7fffd4', '#87cefa', '#f5deb3','#ff69b4','#dc143c'],
    }]
  },
  options: {
    responsive: false,
  }
});

let inputData2 =${incomeList};
//二つ以上グラフを書くときはcontext2,3,4...となんでもいいので名前を変更する
let context2 = document.querySelector("#kimo").getContext('2d')
//上記を変更した場合は、この下の名前（context）も上の名前に合わせる
new Chart(context2, {
type: 'pie',
data: {
  labels: ["うひ", "うほ", "うき", "きき", "んご"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData2[0], inputData2[1],inputData2[2],inputData2[3],inputData2[4],],
    backgroundColor: ['#87cefa', '#7cfc00', '#d2691e','#dda0dd','#ffebcd'],
  }]
},
options: {
  responsive: false,
}
});

let inputData3 =${expenceList};
//二つ以上グラフを書くときはcontext2,3,4...となんでもいいので名前を変更する
let context3 = document.querySelector("#raku").getContext('2d')
//上記を変更した場合は、この下の名前（context）も上の名前に合わせる
new Chart(context3, {
type: 'pie',
data: {
  labels: ["うひ", "うほ", "うき", "きき", "んご"],
  datasets: [{
    //ここで取得した配列の中身を分解して配置する
    data: [inputData3[0], inputData3[1],inputData3[2],inputData3[3],inputData3[4],],
    backgroundColor: ['#87cefa', '#7cfc00', '#d2691e','#dda0dd','#ffebcd'],
  }]
},
options: {
  responsive: false,
}
});
</script>


</html>
