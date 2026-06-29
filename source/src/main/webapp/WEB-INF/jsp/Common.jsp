<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Common.css">
<header class="header">
	<div class="logo">
		<a class="home_logo" href="HomeServlet"><img
			src="images/logo000.png" alt="ウシ管理サイトロゴ"></a>
		<div class="name">
			<c:forEach var="list" items="${userList}">
				こんにちは、<c:out value="${list.name}" />さん!<br>
			</c:forEach>
		</div>
	</div>
	<div class="header-widgets">
		<div class="widget-box weather-box">
			<span id="today-weather">
				${weatherData.strWeather} <br> 
				最高${weatherData.high_temperature}<br>
				最低${weatherData.low_temperature}
			</span>
		</div>
		<!--  
		<div class="widget-box shift-box">
			<span id="today-shift"></span>
		</div>
		-->
		
		<ul>
			<c:forEach var="entry" items="${todayWorkersMap}">
				<c:choose>
					<c:when test="${entry.value == 0}">
						<li>${entry.key.name}（シフト:早朝）</li>
					</c:when>
					<c:when test="${entry.value == 1}">
						<li>${entry.key.name}（シフト:朝）</li>
					</c:when>
					<c:when test="${entry.value == 2}">
						<li>${entry.key.name}（シフト:昼）</li>
					</c:when>
					<c:when test="${entry.value == 3}">
						<li>${entry.key.name}（シフト:夕）</li>
					</c:when>
					<c:when test="${entry.value == 4}">
						<li>${entry.key.name}（シフト:休み）</li>
					</c:when>
				</c:choose>
			</c:forEach>
		</ul>
		
	</div>
</header>

<nav class="nav">
	<ul>
		<li><a href="HomeServlet">ホーム</a></li>
		<li><a href="CowsRegistServlet">ウシ登録</a></li>
		<li><a href="CowsListServlet">ウシ一覧</a></li>
	</ul>
	<ul>
		<li><a href="CowsSearchServlet">ウシ健康</a></li>
		<li><a href="WeatherRegistServlet">天気登録</a></li>
		<li><a href="FeedsManagementServlet">エサ管理</a></li>
		<li><a href="MoneyRegistServlet">収支登録</a></li>
	</ul>
	<ul>
		<li><a href="EmployeesListServlet">従業員一覧</a></li>
		<li><a href="EmployeesRegistServlet">従業員登録</a></li>
	</ul>
	<ul>
		<li><a href="ShiftDisplayServlet">シフト表示</a></li>
		<li><a href="MoneyMonthlyServlet">収支表示</a></li>
	</ul>
	<ul>
		<li><a class="logout" id="outBtn" href="#">ログアウト</a></li>
	</ul>
</nav>
<dialog id="dialog" class="custom-alert" >
        <p class=LO>ログアウトしますか？</p>
        <form method="dialog">
            <button type="submit" class="ok"value="ok">OK</button>
            <button type="submit" class="cancel"value="cancel">Cancel</button>
            <img src="images/ushi_banzai.png" alt="牛のイラスト" height=20% width=20%>
			<img src="images/ushi_banzai.png" alt="牛のイラスト" height=20% width=20%>
			<img src="images/ushi_banzai.png" alt="牛のイラスト" height=20% width=20%>
        </form>
</dialog>
<script type="text/javascript">
        const btn = document.getElementById('outBtn');
        const dialog = document.getElementById('dialog');

        btn.addEventListener('click', function (event) {
            dialog.showModal();
        }, false);

        dialog.addEventListener('close', function () {
            if (dialog.returnValue == "ok") {
            	logout();
            } else {
                console.log("cancelボタンが押されました");
            }
        });
        </script>
<script src="${pageContext.request.contextPath}/js/Common.js"></script>