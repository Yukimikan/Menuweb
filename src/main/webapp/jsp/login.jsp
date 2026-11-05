<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>ログイン</title>
<script>
  function checkText(context) {

    //1. 入力チェック
    var inuser = document.form1.user.value;
    var pw = document.form1.password.value;
    document.form1.message.value = "";

    //1-1 必須
    if(inuser.length === 0) {
        alert('ユーザを指定してください');
        document.form1.message.value ='ユーザを指定してください';
        return false;
    }
    if(pw.length === 0) {
        alert('パスワードを指定して下さい');
        document.form1.message.value ='パスワードを指定して下さい';
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
	<div id="contents">
	<h2>＜ログイン＞</h2>
	</div>
	<div id="main">
		<%
		String message = (String) request.getAttribute("message");
		%>
		<%
		if (message != null && !message.isEmpty()) {
		%>
		<div class="error-box"><%= message %></div>
		<%
		}
		%>
		<form action="LoginServlet" method="post"
   	          onsubmit="return checkText()">
			ユーザ名；<input type="text" name="user" value="user" />
			パスワード<input type="text" name="password" value="" />
	     	<button type="submit" value="login" >ログイン</button>
		</form>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>