<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Common.css">
<header class="header">
    <div class="logo">
        <a class="home_logo" href="HomeServlet"><img src="images/logo000.png" alt="ウシ管理サイトロゴ"></a>
        <div class="name">
            <c:forEach var="list" items="${userList}">
				こんにちは、<c:out value="${list.name}"/>さん!<br>
				この従業員の権限は"<c:out value="${list.admin}"/>"です
			</c:forEach>
        </div>
    </div>
    ${weatherData.strWeather}<br>
     <div class="header-widgets">
        <div class="widget-box weather-box">
            <span id="today-weather" >天気系を取得中...<br>
			${weatherData.high_temperature}<br>
			${weatherData.low_temperature}
            </span>
        </div>
        <div class="widget-box shift-box">
            <span id="today-shift">今日のシフトを取得中...</span>
        </div>
    </div>
    
</header>
    
    <nav class="nav">
        <ul>
            <li><a href="HomeServlet">ホーム</a></li>
            <li><a href="CowsSearchServlet">ウシ健康</a></li>
            <li><a href="CowsListServlet">ウシ一覧</a></li>
        </ul>
        <ul>
            <li><a href="CowsRegistServlet">ウシ登録</a></li>
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
            <li><a class="logout" onclick="logout_check()" href="LogoutServlet">ログアウト</a></li>
        </ul>
    </nav>
