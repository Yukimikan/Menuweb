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
<title>手入力で登録</title>
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
	<!-- ★ CSVリストを受け取る -->
	<!-- List<String> csvList = (List<String>) request.getAttribute("csvFiles") -->
	<form name="form1"
		action="<%= request.getContextPath() %>/CsvConfirmServlet" method="post">
		<div>
			CSV名：
			<div class="input">
				<input type="text" name="csv_name" readonly
				   value="${form.csvName}">
			</div>
		</div>
		<!-- ★ 登録内容表示 -->
		<span>以下登録内容をご確認ください。</span><br>
		<div class="row">
			<div class="label">種別：</div>
			<div class="input">
				<input type="text" name="type" readonly
				   value="${form.type}">
			</div>
		</div>
		<div class="row">
			<div class="label">店名：</div>
			<div class="input">
				<input type="text" name="restaurant_name" readonly
				value="${form.restaurantName}">
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
			<div class="label">メニュー名：</div>
			<div class="input">
				<input type="text" name="menu" readonly
				  value="${form.menu}">
			</div>
		</div>
		<div class="row">
			<div class="label">金額：</div>
			<div class="input">
				<input type="text" name="price" readonly
				  value="${form.price}">円
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
		<div class="row">
			<div align="left">
		 	<a class="btn-arrow back" href="/Menuweb/CsvWriteServlet">戻る</a>
			</div>
		</div>
	</form>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>