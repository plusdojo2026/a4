<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header">
    <div class="logo">
        <a class="home_logo" href="/HomeServlet"><img src="images/cow1.png" alt="ウシ管理サイトロゴ"></a>
        <div class="name">
            <p>こんにちは ${userList.name} さん</p>
        </div>
    </div>
    <nav class="nav">
        <ul>
            <li><a href="HomeServlet">ホーム</a></li>
            <li><a href="CowsDailyServlet">ウシ健康</a></li>
            <li><a href="CowsListServlet">ウシ一覧</a></li>
            <li><a href="CowsRegistServlet">ウシ登録</a></li>
            <li><a href="CowUpdateDeleteServlet">ウシ変更</a></li>
            <li><a href="EmployeesListServlet">従業員一覧</a></li>
            <li><a href="EmployeesRegistServlet">従業員登録</a></li>
            <li><a href="EmployeesUpdateDeleteServlet">従業員変更</a></li>
            <li><a href="FeedsManagementServlet">エサ管理</a></li>
            <li><a href="MoneyMonthlyServlet">収支表示</a></li>
            <li><a href="MoneyRegistServlet">収支登録</a></li>
            <li><a href="ShiftDisplayServlet">シフト表示</a></li>
            <li><a href="WeatherRegistServlet">天気登録</a></li>
            <li><a class="logout" onclick="logout_check()" href="LogoutServlet">ログアウト</a></li>
        </ul>
    </nav>
</header>
