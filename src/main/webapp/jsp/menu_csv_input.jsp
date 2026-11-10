<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  <!-- jspのJSTL式追加用 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resource/css/style.css">
<meta charset="UTF-8">
<title>CSV検索</title>
<script>
  function checkText(context) {

    //1. 入力チェック
    var indate = document.form1.date.value;
    var csvname = document.form1.csv_name.value;
    var total = document.form1.total.value;
    document.form1.message.value = "";

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
    if (total.length === 0) {
    	alert('金額を指定して下さい');
        document.form1.message.value = '金額を指定して下さい';
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
	<div id="contents" class="contents">
	<h2>＜メニュー取込＞</h2>
		プルダウン機能でメニュー一覧を選択。<br>
		1000円以上、店名といった条件で絞り込む。<br>
		＜追加予定＞CSV日付は仮決め。追記機能,店名の登録有無など、
	</div>
	<div id="main">
	<!-- ★ エラーメッセージ表示 -->
    <c:if test="${not empty message}">
    	<div class="error-box">${message}</div>
	</c:if>
	<!-- ★ CSVリストを受け取る -->
	<!-- List<String> csvList = (List<String>) request.getAttribute("csvFiles") -->
	<form name="form1"
		action="<%= request.getContextPath() %>/CsvInputServlet"
		onsubmit="return checkText()" method="post">
		<div>
			CSV名：
	        <select name="csv_name">
		      <c:if test="${empty csvFiles}">
		        <option value="menu.csv">menu.csv</option>
		      </c:if>
	          <c:forEach var="name" items="${csvFiles}">
	            <option value="${name}" />${name}</option>
		      </c:forEach>
		    </select>
		</div>
		<div id="search-input">
			<h3>・検索条件</h3>
			<div class="row">
				<div class="label">日付：</div>
				<div class="input">
					<input type="text" name="date" value="2025/01/01">
				</div>
			</div>
			<div class="row">
				<div class="label">金額（円）：</div>
				<div class="input">
					<input type="text" name="total">円 <select
						name="total_condition">
						<option value="1">以上</option>
						<option value="2">以下</option>
						<option value="3">ピッタリ</option>
						<option value="4">未満</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="label">メニュー絞り込み：</div>
				<div class="input">
					<input type="submit" value="絞込" class="btn-submit">
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