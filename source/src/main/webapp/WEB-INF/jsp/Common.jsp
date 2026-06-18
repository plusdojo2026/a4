<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Common.css">
<header class="header">
    <div class="logo">
        <a class="home_logo" href="HomeServlet"><img src="images/cow1.png" alt="ウシ管理サイトロゴ"></a>
        <div class="name">
            <c:forEach var="list" items="${userList}">
				こんにちは、<c:out value="${list.name}"/>さん!<br>
				この従業員の権限は"<c:out value="${list.admin}"/>"です
			</c:forEach>
        </div>
    </div>
    <nav class="nav">
        <ul>
            <li><a href="HomeServlet">ホーム</a></li>
            <li><a href="CowsSearchServlet">ウシ健康</a></li>
            <li><a href="CowsListServlet">ウシ一覧</a></li>
            <li><a href="CowsUpdateDeleteServlet">ウシ変更</a></li>
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
            <li><a href="ShiftUpdateDeleteServlet">シフト変更</a></li>
            <li><a href="MoneyMonthlyServlet">収支表示</a></li>
        </ul>
        <ul> 
            <li><a class="logout" onclick="logout_check()" href="LogoutServlet">ログアウト</a></li>
        </ul>
    </nav>
</header>
