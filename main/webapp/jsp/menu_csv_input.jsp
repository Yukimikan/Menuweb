<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV検索</title>
</head>
<script>
  function getCsvList(context) {
    document.formcsv.action = "/Menuweb/CsvInputGetServlet";
  }
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
    //2. submit & actionメソッドに遷移先URL(Server)を代入する
    document.form1.action = "/Menuweb/CsvInputServlet";
    return true;
  }
</script>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
	</div>
    <!--
    <form action="<%= request.getContextPath() %>/CsvInputServlet" method="post">
     -->
  	<div id="contents">
	<h2>＜メニュー取込＞</h2>
		プルダウン機能でメニュー一覧を選択。<br>
		1000円以上、店名といった条件で絞り込む。<br>
		＜追加予定＞CSV日付は仮決め。追記機能,店名の登録有無など、
	</div>
	<div id="main">
		<form name="formcsv" onsubmit="return getCsvList()" method="post">
		  CSVリスト取得<input type="submit" value="取得">
		</form>
		<form name="form1" onsubmit="return checkText()" method="post">
		  CSV名：
		  <%
		    List<String> csvList = (List<String>) request.getAttribute("csvList");
		    if (csvList != null && !csvList.isEmpty()) {
		  %>
		    <select name="csv_name">
		      <% for (int i = 0; i < csvList.size(); i++) { %>
		        <option value="<%= csvList.get(i) %>"><%= csvList.get(i) %></option>
		      <% } %>
		    </select>
		  <%
		    } else {
		  %>
		    <select name="csv_name">
		      <option value="menu.csv">menu.csv</option>
		    </select>
		  <%
		    }
		  %>
			 <div id="search-input">
		 	 ・検索条件
			 	 <div id="date">
				  日付：<input type="text" name="date" value="2025/01/01">
			 	 </div>
			 	 <div id="total">
				  金額（円）：<input type="text" name="total">円
				  <select name="total_condition">
				   <option value="1">以上</option>
				   <option value="2">以下</option>
				   <option value="3">ピッタリ</option>
				   <option value="4">未満</option>
				   </select>
			 	 </div>
			 	 <div id="sb">
			 	 メニュー絞り込み<input type="submit" value="絞込">
			 	 </div>
			 	 <div id="message">
				  メッセージ： <input type="text" name="message" disabled />
				  </div>
  		     </div>
		 </form>
	     <!--
	     	     <input type="text" name="message" value="<%= request.getAttribute("message") %>" disabled />
	      -->
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a>
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>