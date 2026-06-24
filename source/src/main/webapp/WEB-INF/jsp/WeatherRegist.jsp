<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>天気登録画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/WeatherRegist.css">

</head>
<%@ include file="Common.jsp" %>
<body>
	<div id="dvd-background-container">
    <img class="dvd-logo" src="${pageContext.request.contextPath}/images/cow2.jpg" alt="牛1">
    <img class="dvd-logo" src="${pageContext.request.contextPath}/images/cow2.jpg" alt="牛2">
    <img class="dvd-logo" src="${pageContext.request.contextPath}/images/cow2.jpg" alt="牛3">
	</div>
<div class="title">
	<h1>☀ 天気の登録画面 ☔</h1>
</div>
<div class="title2">
	<div>↓登録は1日1回まで</div>
	<span style="color: red;">${message}</span>
</div>
	<div class="container">
		<div class="left-content">
		<button id="btn" class="speechBubble">①天気取得は<br>このボタンから！</button>
		<img src="images/weathercock0.png" alt="牛のイラスト" height=60% width=60%> 
		</div>
		<div class="right-content">
			<form method="POST" action="${pageContext.request.contextPath}/WeatherRegistServlet"> <div id="errorArea">${errorMsg}</div><br> 
				<div class="date-row">	
					<label for="timer">登録対象日 (今日)：</label>
					<input type="text" id="timer" name="day" placeholder="日付"readonly><br>
					<input type="hidden" id="weatherCode" name="weatherCode">
				</div>
			<table class="tenki-table">
				<thead>
					<tr>
						<td>天候 (例 : 雨)<br></td>
						<td>最高気温 (℃)<br></td>
						<td>最低気温 (℃)<br></td>
						<td><input type="text" id="weatherText" name="tenki" placeholder="天候"readonly required><br></td>
						<td><input type="text" id="highKion" name="high_kion" placeholder="最高気温"readonly><br></td>
						<td><input type="text" id="lowKion" name="low_kion" placeholder="最低気温"readonly><br></td>
						
					</tr>
				</thead>
				 <tbody>
					<tr>
						<td>平均湿度 (%)<br></td>
						<td>降水量 (mm)<br></td>
						<td>風力 (m/s)<br></td>
						<td><input type="text" id="humidity" name="humidity" placeholder="平均湿度"readonly><br></td>
						<td><input type="text" id="precipitation" name="pre" placeholder="降水量"readonly><br></td>
						<td><input type="text" id="windSpeed" name="windpower" placeholder="風力"readonly><br></td>
					</tr>
				</tbody>
			</table>
				<button type="submit" id="send-btn" class="sub-btn" name="regist" value="weatherRegist" disabled>②これで送信</button>
			</form>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/WeatherRegist.js"></script>
	<script>
	const form = document.getElementById("weatherText");
	const button = document.getElementById("send-btn");
	
	form.addEventListener("input", update);
	function update() {
		  const isRequired = form.checkValidity();
	}
	
	function update() {
		  const isRequired = form.checkValidity();

		  if (isRequired) {
		    button.disabled = false;
		    return;
		  }
		}
	</script>
	<script>
	document.addEventListener('DOMContentLoaded', () => {
		// 画面内の背景画像（class="dvd-logo"）を取得
		const logoElements = document.querySelectorAll('.dvd-logo');
		if (logoElements.length === 0) return; // 画像がなければ処理を終了

		// 各画像の「位置」と「速度」のデータを保存する配列
		const items = [];

		// 画像ごとに初期設定
		logoElements.forEach((logo) => {
			// CSSを適用
			logo.style.position = 'absolute';
			logo.style.width = '120px'; // 画像の横幅を120px固定
			logo.style.height = 'auto';
			logo.style.opacity = '0.3'; // 透明度を30%

			// 動く速度設定
			const speedX = (Math.random() > 0.5 ? 1 : -1) * (0.8 + Math.random() * 0.6);
			const speedY = (Math.random() > 0.5 ? 1 : -1) * (0.8 + Math.random() * 0.6);

			// 初期の進行方向）に合わせて、画像の向き設定
			logo.style.transform = speedX < 0 ? 'scaleX(-1)' : 'scaleX(1)';

			// 各画像の個別データを配列に保存
			items.push({
				element: logo,
				posX: Math.random() * (window.innerWidth - 120),  // 画面内のランダムなX座標
				posY: Math.random() * (window.innerHeight - 120), // 画面内のランダムなY座標
				speedX: speedX,
				speedY: speedY
			});
		});

		// 画像を動かし、壁にぶつかったら跳ね返らせるメイン処理
		function moveAll() {
			const screenWidth = window.innerWidth;   // ブラウザの横幅
			const screenHeight = window.innerHeight; // ブラウザの高さ

			// 配列に登録された画像を1枚ずつループ処理
			items.forEach(item => {
				const logo = item.element;
				const logoWidth = 120;
				const logoHeight = logo.offsetHeight || 120; // 縦幅を取得（失敗したら120px）

				// 画像の座標を速度分だけ進める
				item.posX += item.speedX;
				item.posY += item.speedY;

				// 横方向の衝突。判定右端にぶつかる、または左端にぶつかった場合
				if (item.posX + logoWidth >= screenWidth || item.posX <= 0) {
					item.speedX = -item.speedX; // 進行方向を反対にする
					item.posX = item.posX <= 0 ? 0 : screenWidth - logoWidth; // 画面外へのはみ出し防止

					// 壁にぶつかって横の向きが変わったので、画像の顔の向き左右反転
					logo.style.transform = item.speedX < 0 ? 'scaleX(1)' : 'scaleX(-1)';
				}

				// 縦方向の衝突判定。下端にぶつかる、または上端にぶつかった場合
				if (item.posY + logoHeight >= screenHeight || item.posY <= 0) {
					item.speedY = -item.speedY; // 進行方向を反対にする
					item.posY = item.posY <= 0 ? 0 : screenHeight - logoHeight; // 画面外へのはみ出し防止
				}

				// 計算した新しい座標を、実際の画像スタイルに反映させて動かす
				logo.style.left = item.posX + 'px';
				logo.style.top = item.posY + 'px';
			});

			// リフレッシュレート合わせmoveAllを無限ループ
			requestAnimationFrame(moveAll);
		}

		// アニメーションスタート
		moveAll();
	});
</script>
</body>
</html>