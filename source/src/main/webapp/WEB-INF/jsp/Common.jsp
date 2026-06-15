<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header class="header">
        <div class="logo">
            <a class="home_logo" href="/HomeServlet"><img src="images/cow1.png" alt="名刺管理サイトロゴ"></a>
        	<div class="date">
            	<p>こんにちは ${name} さん</p>
        	</div>
		</div>
        <nav class="nav">
            <ul>
                <li><a href="HomeServlet">ホーム</a></li>
                <li><a href="WeatherRegistServlet">名刺検索</a></li>
                <li><a href="CowUpdateDeleteServlet">名刺登録</a></li>
                <li><a href="AbstServlet">お問い合わせ</a></li>
                <li><a class="logout" onclick="logout_check()" href="LogoutServlet">ログアウト</a></li>
            </ul>
        </nav>
	</header>
</body>
</html>