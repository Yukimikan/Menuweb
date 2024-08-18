<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV取込</title>
</head>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・CSV取込
	</div>
	<div id="main">
	     <form action="<%= request.getContextPath() %>/CSVInputServlet" method="post">
	         日付：<input type="text" name="date">
	         CSV名：<input type="text" name="csv_name" value="menu.csv">
	                 <input type="submit" value="CSV読込">
	     </form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a> 
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>