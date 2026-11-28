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
</head>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
	<div id="contents" class="contents">
	<h2>＜メニュー検索＞</h2>
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
			<div id="search-input">
				<h3>・検索条件</h3>
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
						    <option value=""
						        ${empty form.singleMenuFlg ? 'selected' : ''}>-</option>
						    <option value="0"
						        ${form.singleMenuFlg == '0' ? 'selected' : ''}>セット</option>
						    <option value="1"
						        ${form.singleMenuFlg == '1' ? 'selected' : ''}>単品</option>
     					</select>
					</div>
				</div>
				<div class="row">
					<div class="label">金額（店内飲食）：</div>
					<div class="input">
						<input type="text" name="eat_in_price" value="${form.eatInPrice}">円
						<select name="eat_in_condition">
						    <option value=""
						        ${empty form.eatInCondition ? 'selected' : ''}>-</option>
							<option value="1"
							    ${form.eatInCondition == '1' ? 'selected' : ''}>以上</option>
							<option value="2"
							    ${form.eatInCondition == '2' ? 'selected' : ''}>以下</option>
							<option value="3"
							    ${form.eatInCondition == '3' ? 'selected' : ''}>ピッタリ</option>
							<option value="4"
							    ${form.eatInCondition == '4' ? 'selected' : ''}>未満</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="label">金額（テイクアウト）：</div>
					<div class="input">
						<input type="text" name="takeout_price" value="${form.takeoutPrice}">円
						<select name="takeout_condition">
						    <option value=""
						        ${empty form.takeoutCondition ? 'selected' : ''}>-</option>
							<option value="1"
							    ${form.takeoutCondition == '1' ? 'selected' : ''}>以上</option>
							<option value="2"
							    ${form.takeoutCondition == '2' ? 'selected' : ''}>以下</option>
							<option value="3"
							    ${form.takeoutCondition == '3' ? 'selected' : ''}>ピッタリ</option>
							<option value="4"
							    ${form.takeoutCondition == '4' ? 'selected' : ''}>未満</option>
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