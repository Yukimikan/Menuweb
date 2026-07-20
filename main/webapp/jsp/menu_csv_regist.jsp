<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>手入力で登録</title>
<script>
// 入力チェック（フロント側）
function checkText() {

  var csvname = document.form1.csv_name.value;
  var type = document.form1.type.value;
  var restaurant = document.form1.restaurant_name.value;
  var menu = document.form1.menu.value;
  var price = document.form1.price.value;
  var tax = document.form1.tax.value;
  var total = document.form1.total.value;

  document.form1.message.value = "";

  if (csvname.length === 0) {
    alert('CSV名を指定して下さい');
    document.form1.message.value = 'CSV名を指定して下さい';
    return false;
  }
  if (type.length === 0) {
    alert('種別を指定して下さい');
    document.form1.message.value = '種別を指定して下さい';
    return false;
  }
  if (restaurant.length === 0) {
    alert('店名を指定して下さい');
    document.form1.message.value = '店名を指定して下さい';
    return false;
  }
  if (menu.length === 0) {
    alert('メニュー名を指定して下さい');
    document.form1.message.value = 'メニュー名を指定して下さい';
    return false;
  }
  if (price.length === 0) {
    alert('金額を指定して下さい');
    document.form1.message.value = '金額を指定して下さい';
    return false;
  }
  if (tax.length === 0) {
    alert('税額を指定して下さい');
    document.form1.message.value = '税額を指定して下さい';
    return false;
  }
  if (total.length === 0) {
    alert('合計金額を指定して下さい');
    document.form1.message.value = '合計金額を指定して下さい';
    return false;
  }

  return true;
}
</script>
</head>
<body>
	<header class="header-nav">
		<nav class="breadcrumb">
			<h4>1000円ランチシステム</h4>
			<!-- ここだけページごとに差し替える -->
			<a href="<%=request.getContextPath()%>/index.html">TOP</a>
			<span>手入力で登録</span>
		</nav>
	</header>
  	<div id="contents">
	<h2>＜メニュー登録＞</h2>
		プルダウン機能でメニュー一覧を選択。<br>
		各メニュー項目の新規書込みを行う。
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
			action="<%= request.getContextPath() %>/CsvWriteServlet"
			onsubmit="return checkText()" method="post">

			<div>
				CSV名： <select name="csv_name">
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

			<div id="regist-input">

				<div class="row">
					<div class="label">種別：</div>
					<div class="input">
						<input type="text" name="type">
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
	<footer class="footer-nav">

		<!-- 戻る（ここだけページごとに差し替える） -->
		<a class="btn-arrow back" href="<%= request.getContextPath() %>/index.html">戻る</a>

		<!-- TOP（画像ボタン） -->
		<a href="<%=request.getContextPath()%>/index.html"> <img
			class="btn-top"
			src="<%=request.getContextPath()%>/resource/image/btn_top.png">
		</a>

		<!-- 進む（必要なページだけ差し替え or 非表示） -->
		<a class="btn-arrow next" href="<%= request.getContextPath() %>/jsp/menu_result.jsp">進む</a>

	</footer>
</body>
</html>