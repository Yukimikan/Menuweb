<%@ page contentType="text/html; charset=UTF-8" %>
<header class="header-nav">
	<nav class="breadcrumb">
		<h4>1000円ランチシステム</h4>
		<!-- 入力画面を表示 -->
		<a href="./index.html">TOP</a>
		<a href="/Menuweb/csv_search">CSV検索</a>
	    <a href="/Menuweb/csv_write">手入力で登録</a>
	    <a href="/Menuweb/login">ログイン</a>
		<span>ユーザ名：</span>
		    <c:if test="${not empty sessionScope.userName}">
		        <span>${sessionScope.userName}</span>
		    </c:if>
		    <!--  非ログイン時（ログイン機能実装後の動確用） -->
		    <c:if test="${empty sessionScope.userName}">
		        <span>guestユーザ</span>
		    </c:if>
	</nav>
</header>
