<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV登録</title>
</head>
<body>
	<div id="header">
		<h4>1000円ランチシステム</h4>
		・CSV登録
	</div>
	<div id="main">
	     <form action="<%= request.getContextPath() %>/CSVWriteServlet" method="post">
			<table border="1">
				<tr>
					<td>No</td>
					<td>種類</td>
					<td>店名</td>
					<td>単品</td>
					<td>メニュー</td>
					<td>価格</td>
					<td>税</td>
					<td>金額</td>
				</tr>
				<tr>
					<th><input type="text" name="no" value=""></th>
					<th><input type="text" name="type" value="和食"></th>
					<th><input type="text" name="restaurant_name" value="松屋"></th>
					<th><input type="text" name="singlemenu_flg" value="○"></th>
					<th><input type="text" name="menu" value="キムカル丼"></th>
					<th><input type="text" name="price" value="100"></th>
					<th><input type="text" name="tax" value="なし"></th>
					<th><input type="text" name="total" value="300"></th>
				</tr>
			</table>
            <input type="submit" value="CSV書込">
	     </form>
	</div>
	<div id="footer">
		<a href="../index.html">トップに戻る</a> 
		<input type="button" onclick="history.back()" value="前に戻る">
	</div>
</body>
</html>