<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.MenuCSV"%>
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
	<header class="header-nav">
		<nav class="breadcrumb">
			<h4>1000円ランチシステム</h4>
			<!-- ここだけページごとに差し替える -->
			<a href="<%=request.getContextPath()%>/index.html">TOP</a> <a
				href="<%=request.getContextPath()%>/jsp/menu_regist.jsp">手入力</a>
			<span>CSV検索結果</span>
		</nav>
	</header>
	<div id="contents">
		<h2>＜検索結果＞</h2>
		メニュー検索の結果を表示します。
	</div>
	<div id="main">
	<%
      List<MenuCSV> list = (List<MenuCSV>) request.getAttribute("resultList");
      String error = (String) request.getAttribute("error");
    %>
	<!-- ★ エラー表示 -->
		<%
		if (error != null) {
		%>
		<div style="color: red;"><%=error%></div>
		<%
		}
		%>
		<!-- ★ 結果表示 -->
		<%
		if (list != null && !list.isEmpty()) {
		%>
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
		<%
		} else {
		%>
		<div>該当するメニューはありません。</div>
		<%
		}
		%>
		<!--
       	<input type="submit" value="登録・編集">
        -->
	</div>
	<footer class="footer-nav">

		<!-- 戻る（ここだけページごとに差し替える） -->
		<a class="btn-arrow back" href="<%= request.getContextPath() %>/jsp/menu_regist.jsp">戻る</a>

		<!-- TOP（画像ボタン） -->
		<a href="<%=request.getContextPath()%>/index.html"> <img
			class="btn-top"
			src="<%=request.getContextPath()%>/resource/image/btn_top.png">
		</a>
		<!-- NEXT は不要なら削除 -->
		<!-- 進む（必要なページだけ差し替え or 非表示） -->
		<!--
		 <a class="btn-arrow next" href="###NEXT###">進む</a>
		-->
	</footer>
</body>
</html>