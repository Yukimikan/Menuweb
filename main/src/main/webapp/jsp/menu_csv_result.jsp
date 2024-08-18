<%@page import="java.util.ArrayList"%>
<%@page import="model.MenuCSV"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV検索結果</title>
<!-- 参考情報 -->
<!-- https://anotools.com/java/1642/ -->
</head>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・CSV検索結果
	</div>
	<div id="main">
		<form action="<%= request.getContextPath() %>/CSVControlServlet"
			method="post">
			<%
			ArrayList<MenuCSV> retList = (ArrayList<MenuCSV>)request.getAttribute("retList");		
			if (retList == null || retList.size() == 0){
		%>
			<br>
			<P>検索結果なし</P>
			<br>
			<%	} else { %>
			<table border="1">
				<tr>
					<td>No</td>
					<td>種類</td>
					<td>店名</td>
					<td>単品</td>
					<td>メニュー</td>
					<td>価格</td>
					<td>税</td>
					<td>金額</td>
				</tr>
				<%	for(int i = 0; i < retList.size(); i++){ %>
				<tr>
					<th><%= retList.get(i).getNo() %></th>
					<th><%= retList.get(i).getType() %></th>
					<th><%= retList.get(i).getRestaurant_name() %></th>
					<th><%= retList.get(i).getSinglemenu_flg() %></th>
					<th><%= retList.get(i).getMenu() %></th>
					<th><%= retList.get(i).getPrice() %></th>
					<th><%= retList.get(i).getTax() %></th>
					<th><%= retList.get(i).getTotal() %></th>
				</tr>
				<% } %>
			</table>
			<input type="submit" value="登録・編集">
			<% } %>
		</form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a> 
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>