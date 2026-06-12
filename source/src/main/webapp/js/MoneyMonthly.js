/**
 * 円グラフのjs
 */
 
 <script>
//顔文字のドーナツチャートの部分の処理
let inputData =${kaoList};
let context = document.querySelector("#kimochi").getContext('2d')
new Chart(context, {
  type: 'doughnut',
  data: {
    labels: ["にっこり", "ちょっとにっこり", "普通", "ちょっと不満", "不満"],
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

let inputData2 =${xxList};
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
</script>