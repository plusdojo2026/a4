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
let inputData =${kaoList};
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
</script>


</html>
