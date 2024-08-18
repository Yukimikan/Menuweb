<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・検索結果
	</div>
	<div id="main">
         日付：<input type="text" name="date" value=<%= request.getParameter("date") %>>
         金額（円）：<input type="text" name="total" value=<%= request.getParameter("total") %>>円
    </div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a> 
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>