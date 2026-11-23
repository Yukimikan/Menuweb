<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List"%>
<%@ page import="model.MenuCSV"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>登録確認</title>
<script>
// 入力チェック（フロント側）
</script>
</head>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
  	<div id="contents">
	<h2>＜メニュー確認＞</h2>
		登録確認画面<br>
	</div>
	<div id="main">
	<!-- ★ エラーメッセージ表示 -->
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
		<form name="form1"
			action="${pageContext.request.contextPath}/menu_confirm" method="post">
			<div>
				ユーザ名：
				<div class="input">
				    <c:if test="${not empty sessionScope.userName}">
					<input type="text" name="user_name" readonly
					   value="${sessionScope.userName}">
					</c:if>
				    <!--  非ログイン時（ログイン機能実装後の動確用） -->
				    <c:if test="${empty sessionScope.userName}">
		                <input type="text" name="user_name" value="guest">
				    </c:if>
				</div>
			</div>
			<!-- ★ 登録内容表示 -->
			<span>以下登録内容をご確認ください。</span><br>
			<div class="row">
				<div class="label">店名：</div>
				<div class="input">
					<input type="text" name="restaurant_name" readonly
					value="${form.restaurantName}">
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー名：</div>
				<div class="input">
					<input type="text" name="menu_name" readonly
					  value="${form.menuName}">
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー種類：</div>
				<div class="input">
					<input type="text" name="category" readonly
					  value="${form.category}">
				</div>
			</div>
			<div class="row">
				<div class="label">単品フラグ：</div>
				<div class="input">
					<input type="text" name="singlemenu_flg" readonly
					      value="${form.singleMenuFlg}">
				</div>
			</div>
			<div class="row">
				<div class="label">金額（店内）：</div>
				<div class="input">
					<input type="text" name="eat_in_price" readonly
					  value="${form.eatInPrice}">円
				</div>
			</div>
			<div class="row">
				<div class="label">金額（テイクアウト）：</div>
				<div class="input">
					<input type="text" name="takeout_price" readonly
					  value="${form.takeoutPrice}">円
				</div>
			</div>
			<div class="row">
				<div class="label">税額：</div>
				<div class="input">
					<input type="text" name="tax" readonly
					  value="${form.tax}">円
				</div>
			</div>
			<div class="row">
				<div class="label">合計：</div>
				<div class="input">
					<input type="text" name="total" readonly
					  value="${form.total}">円
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー登録：</div>
				<div class="input">
					<input type="submit" value="登録" class="btn-submit">
				</div>
			</div>
		</form>
		<div class="row">
			<div align="left">
			<form action="${pageContext.request.contextPath}/menu_regist" method="post">
				<!-- hidden parameter -->
			    <input type="hidden" name="from_screen" value="confirm">
			    <input type="hidden" name="restaurant_name" value="${form.restaurantName}">
			    <input type="hidden" name="menu_name" value="${form.menuName}">
			    <input type="hidden" name="category" value="${form.category}">
			    <input type="hidden" name="singlemenu_flg" value="${form.singleMenuFlg}">
			    <input type="hidden" name="eat_in_price" value="${form.eatInPrice}">
			    <input type="hidden" name="takeout_price" value="${form.takeoutPrice}">
			    <input type="hidden" name="tax" value="${form.tax}">
			    <input type="hidden" name="total" value="${form.total}">
			    <input type="submit" value="戻る" class="btn-submit">
			</form>
			</div>
		</div>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>