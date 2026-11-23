<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List"%>
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
			action="${pageContext.request.contextPath}/restaurant_confirm" method="post">
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
				<div class="label">種類：</div>
				<div class="input">
					<input type="text" name="type" readonly
					  value="${form.type}">
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
			<form action="${pageContext.request.contextPath}/restaurant_regist" method="post">
				<!-- hidden parameter -->
			    <input type="hidden" name="from_screen" value="confirm">
			    <input type="hidden" name="restaurant_name" value="${form.restaurantName}">
			    <input type="hidden" name="type" value="${form.type}">
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