<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.MenuCSV"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>手入力で登録</title>
<script>
// 入力チェック（フロント側）
</script>
</head>
<body>
	<div id="header" class="header">
	<%@include file="./header.jsp"%>
	</div>
  	<div id="contents">
	<h2>＜メニュー確認＞</h2>
		登録確認画面<br>
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
	<%
      List<String> csvList = (List<String>) request.getAttribute("csvFiles");
      String error = (String) request.getAttribute("error");
    %>

    <!-- ★ エラー表示（サーバー側例外時） -->
    <%
      if (error != null) {
    %>
      <div style="color:red;"><%= error %></div>
    <%
      }
    %>
		<form name="form1"
			action="<%= request.getContextPath() %>/CsvWriteServlet" method="post">
			<div>
				CSV名： <select name="csv_name" readonly>
					<%
            if (csvList != null && !csvList.isEmpty()) {
              for (String name : csvList) {
          %>
					<option value="<%= name %>"><%= name %></option>
					<%
              }
            } else {
          %>
					<option value="menu.csv">menu.csv</option>
					<%
            }
          %>
				</select>
			</div>
			<!-- ★ 登録内容表示 -->
				<div class="row">
					<div class="label">種別：</div>
					<div class="input">
						<input type="text" name="type" value=<%=request.getAttribute("type") %>>
					</div>
				</div>

				<div class="row">
					<div class="label">店名：</div>
					<div class="input">
						<input type="text" name="restaurant_name">
					</div>
				</div>

				<div class="row">
					<div class="label">単品フラグ：</div>
					<div class="input">
						<select name="singlemenu_flg">
							<option value="0">セット</option>
							<option value="1">単品</option>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="label">メニュー名：</div>
					<div class="input">
						<input type="text" name="menu">
					</div>
				</div>

				<div class="row">
					<div class="label">金額：</div>
					<div class="input">
						<input type="text" name="price">円
					</div>
				</div>

				<div class="row">
					<div class="label">税額：</div>
					<div class="input">
						<input type="text" name="tax">円
					</div>
				</div>

				<div class="row">
					<div class="label">合計：</div>
					<div class="input">
						<input type="text" name="total">円
					</div>
				</div>

				<div class="row">
					<div class="label">メニュー登録：</div>
					<div class="input">
						<input type="submit" value="登録" class="btn-submit">
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="footer" class="footer">
	<%@include file="./footer.jsp"%>
	</div>
</body>
</html>