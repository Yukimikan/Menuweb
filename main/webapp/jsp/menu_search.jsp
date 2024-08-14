<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索条件</title>
</head>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・検索条件
	</div>
	<div id="main">
		<form action="<%= request.getContextPath() %>/SearchMenuServlet"
			method="post">
			日付：<input type="text" name="date"> 
			金額（円）：<input type="text"	name="total">円 
			<input type="submit" value="送信">
		</form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a> 
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>