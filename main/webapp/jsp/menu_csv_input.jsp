<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV取込</title>
</head>
<script>
  function checkText(context) {

    //1. 入力チェック
    var indate = document.form1.date.value;
    var csvname = document.form1.csv_name.value;

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
    //2. submit & actionメソッドに遷移先URL(Server)を代入する
    document.form1.action = "/Menuweb/CsvInputServlet";
  }
</script>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・CSV取込
	</div>
    <!--
    <form action="<%= request.getContextPath() %>/CsvInputServlet" method="post">
     -->
	<div id="main">
	     <form name="form1" onsubmit="return checkText()" method="post">
	         日付：<input type="text" name="date">
	         CSV名：<input type="text" name="csv_name" value="menu.csv">
	                 <input type="submit" value="CSV読込">
	     メッセージ：<input type="text" name="message" value="<%= request.getAttribute("message") %>" disabled />
	     </form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a>
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>