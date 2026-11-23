<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<link rel="stylesheet" href="resource/css/menu_search.css">
<meta charset="UTF-8">
<title>メニュー検索（テーブル検索）</title>
<script>
  function checkText(context) {

    //1. 入力チェック
    var indate = document.form1.date.value;
    var csvname = document.form1.csv_name.value;
    document.form1.message.value = "";

    //1-1 必須
    if(indate.length === 0) {
        alert('日付を指定して下さい');
        document.form1.message.value ='日付を指定して下さい';
        return false;
    }
    if(csvname.length === 0) {
        alert('CSV名を指定して下さい');
        document.form1.message.value ='CSV名を指定して下さい';
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
	<div id="contents" class="contents">
	<h2>＜メニュー取込＞</h2>
		プルダウン機能でメニュー一覧を選択。<br>
		1000円以上、店名といった条件で絞り込む。<br>
	</div>
	<div id="main">
	<!-- ★ エラーメッセージ表示 -->
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
		<form name="form1"
			action="${pageContext.request.contextPath}/menu_search"
			onsubmit="return checkText()" method="post">
			<div>
				ユーザ名：
			    <c:if test="${not empty sessionScope.userName}">
			        <input type="text" name="user_name" value="${sessionScope.userName}">
			    </c:if>
			    <!--  非ログイン時（ログイン機能実装後の動確用） -->
			    <c:if test="${empty sessionScope.userName}">
	                <input type="text" name="user_name" value="guest">
			    </c:if>
			</div>
			<div id="search-input">
				<h3>・検索条件</h3>
				<div class="row">
					<div class="label">日付：</div>
					<div class="input">
						<input type="text" name="date" value="${form.date}">
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
					<div class="label">金額（店内飲食）：</div>
					<div class="input">
						<input type="text" name="eat_in_price" value="${form.eatInPrice}">円
						<select name="eat_in_condition">
							<option value="1">以上</option>
							<option value="2">以下</option>
							<option value="3">ピッタリ</option>
							<option value="4">未満</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="label">金額（テイクアウト）：</div>
					<div class="input">
						<input type="text" name="takeout_price" value="${form.takeoutPrice}">円
						<select name="takeout_condition">
							<option value="1">以上</option>
							<option value="2">以下</option>
							<option value="3">ピッタリ</option>
							<option value="4">未満</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="label">金額（合計）：</div>
					<div class="input">
						<input type="text" name="total" value="${form.total}">円
						<select name="total_condition">
							<option value="1">以上</option>
							<option value="2">以下</option>
							<option value="3">ピッタリ</option>
							<option value="4">未満</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="label">メニュー絞り込み：</div>
					<div class="input">
						<input type="submit" value="絞込" class="btn-submit">
					</div>
				</div>
			</div>
		</form>
		<div id="search-result">
			<!-- ★ 結果表示 -->
			<c:choose>
			    <c:when test="${count > 0}">
			        検索結果：${count}件
			    </c:when>
			    <c:otherwise>
			        検索結果はありません。
			    </c:otherwise>
			</c:choose>
	        <c:if test="${not empty menuList}">
	  		<table border="1" bgcolor="white" class="search-result-table">
				<tr>
					<th>No</th>
					<th>店舗ID</th>
					<th>店名</th>
					<th>メニュー</th>
					<th>種類</th>
					<th>単品</th>
					<th>金額（店内飲食）</th>
					<th>金額（テイクアウト）</th>
					<th>税</th>
					<th>金額（合計）</th>
				</tr>
	            <c:forEach var="rec" items="${menuList}">
				<tr>
					<td><c:out value="${rec.no}" /></td>
					<td><c:out value="${rec.restaurantId}" /></td>
					<td><c:out value="${rec.restaurantName}" /></td>
					<td><c:out value="${rec.menuName}" /></td>
					<td><c:out value="${rec.category}" /></td>
					<td><c:if test="${rec.singleMenuFlg == '1'}">○</c:if></td>
					<td><c:out value="${rec.eatInPrice}" /></td>
					<td><c:out value="${rec.takeoutPrice}" /></td>
					<td><c:out value="${rec.tax}" /></td>
					<td><c:out value="${rec.total}" /></td>
				</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>