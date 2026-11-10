<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.MenuCSV"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>CSV検索結果</title>
<!-- 参考情報 -->
<!-- https://anotools.com/java/1642/ -->
</head>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
	<div id="contents">
		<h2>＜検索結果＞</h2>
		メニュー検索の結果を表示します。
	</div>
	<div id="main">
	<!-- ★ エラーメッセージ表示 -->
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
	<%
      List<MenuCSV> list = (List<MenuCSV>) request.getAttribute("resultList");
    %>
		<!-- ★ 結果表示 -->
        <c:if test="${not empty resultList}">
  		<table border="1">
			<tr>
				<th>No</th>
				<th>種類</th>
				<th>店名</th>
				<th>単品</th>
				<th>メニュー</th>
				<th>価格</th>
				<th>税</th>
				<th>金額</th>
			</tr>
			<%
			for (MenuCSV rec : list) {
			%>
			<tr>
				<td><%=rec.getNo()%></td>
				<td><%=rec.getType()%></td>
				<td><%=rec.getRestaurantName()%></td>
				<td><%=rec.getSinglemenuFlg()%></td>
				<td><%=rec.getMenu()%></td>
				<td><%=rec.getPrice()%></td>
				<td><%=rec.getTax()%></td>
				<td><%=rec.getTotal()%></td>
			</tr>
			<%
			}
			%>
		</table>
		</c:if>
        <c:if test="${empty resultList}">
			<div>該当するメニューはありません。</div>
		</c:if>
		<!--
       	<input type="submit" value="登録・編集">
        -->
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>