<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>メニュー登録（テーブル登録）</title>
<script>
// 入力チェック（フロント側）
function checkText() {

  var restaurant = document.form1.restaurant_name.value;
  var tax = document.form1.tax.value;
  var total = document.form1.total.value;

  document.form1.message.value = "";

  if (restaurant.length === 0) {
    alert('店名を指定して下さい');
    document.form1.message.value = '店名を指定して下さい';
    return false;
  }
  if (tax.length === 0) {
    alert('税額を指定して下さい');
    document.form1.message.value = '税額を指定して下さい';
    return false;
  }
  if (total.length === 0) {
    alert('合計金額を指定して下さい');
    document.form1.message.value = '合計金額を指定して下さい';
    return false;
  }

  return true;
}
</script>
</head>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
  	<div id="contents">
	<h2>＜メニュー登録＞</h2>
	     メニューの新規登録を行う。
	</div>
	<div id="main">
	<!-- ★ エラーメッセージ表示 -->
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
	<form name="form1"
		action="${pageContext.request.contextPath}/menu_regist"
		onsubmit="return checkText()" method="post">
		<div>
			ユーザ名：
		    <c:if test="${not empty sessionScope.userName}">
		        <input type="text" name="user_name" value="${sessionScope.userName}" readonly>
		    </c:if>
		    <!--  非ログイン時（ログイン機能実装後の動確用） -->
		    <c:if test="${empty sessionScope.userName}">
                <input type="text" name="user_name" value="guest" readonly>
		    </c:if>
		</div>
		<div id="regist-input">
			<div class="row">
				<div class="label">確認画面を省略する</div>
				<div class="input">
					<input type="checkbox" name="confirm_flg" value="${form.confirmFlg}" checked>
				</div>
			</div>
			<!--
			<div class="row">
				<div class="label">種別：</div>
				<div class="input">
					<input type="text" name="type" value="${form.type}">
				</div>
			</div>
			 -->
			<div class="row">
				<div class="label">店舗ID：</div>
				<div class="input">
					<input type="text" name="restaurant_id" value="${form.restaurantId}">
				</div>
			</div>
			<div class="row">
				<div class="label">店名：</div>
				<div class="input">
					<input type="text" name="restaurant_name" value="${form.restaurantName}">
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー名：</div>
				<div class="input">
					<input type="text" name="menu_name" value="${form.menuName}">
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー種類：</div>
				<div class="input">
					<input type="text" name="category" value="${form.category}">
				</div>
			</div>
			<div class="row">
				<div class="label">単品フラグ：</div>
				<div class="input">
					<select name="singlemenu_flg">
						<option value="0">セット</option>
						<option value="1">単品</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="label">金額（店内）：</div>
				<div class="input">
					<input type="text" name="eat_in_price" value="${form.eatInPrice}">円
				</div>
			</div>
			<div class="row">
				<div class="label">金額（テイクアウト）：</div>
				<div class="input">
					<input type="text" name="takeout_price" value="${form.takeoutPrice}">円
				</div>
			</div>
			<div class="row">
				<div class="label">税額：</div>
				<div class="input">
					<input type="text" name="tax" value="${form.tax}">円
				</div>
			</div>
			<div class="row">
				<div class="label">合計：</div>
				<div class="input">
					<input type="text" name="total" value="${form.total}">円
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー登録：</div>
				<div class="input">
					<input type="submit" value="登録" class="btn-submit">
				</div>
			</div>
		</div>
	</form>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>