<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索条件</title>
<script>
  function getCsvList(context) {
    document.formcsv.action = "/Menuweb/CsvInputGetServlet";
  }
</script>
</head>
<body>
<!-- 廃止
	<div id="header">
		<h4>1000円ランチシステム</h4>
	</div>
  	<div id="contents">
	<h2>＜メニュー検索＞</h2>
		Menu.csvに登録したデータの検索。<br>
		1000円以上、店名といった条件で絞り込む。<br>
		＜追加予定＞店名の登録有無など、
	</div>
	<div id="main">
		<form action="<%= request.getContextPath() %>/SearchMenuServlet"
			method="post">
		・検索条件
			日付：<input type="text" name="date">
			金額（円）：<input type="text"	name="total">円
			<input type="submit" value="送信">
	     メッセージ：<input type="text" name="message" value="<%= request.getAttribute("message") %>" disabled />
		</form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a>
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
 -->
</body>
</html>