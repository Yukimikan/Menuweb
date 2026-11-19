<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>エラー画面</title>
</head>
<script></script>
<body>
    <span color="red">error</span>
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
	<!-- ★ エラー表示（サーバー側例外時） -->
    <c:if test="${not empty error}">
    	<div class="error-box">${error}</div>
	</c:if>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>