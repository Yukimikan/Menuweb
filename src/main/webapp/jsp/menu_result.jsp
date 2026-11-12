<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>登録完了画面</title>
</head>
<script></script>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
	<div id="main">
    <span>登録を完了しました。</span>
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>