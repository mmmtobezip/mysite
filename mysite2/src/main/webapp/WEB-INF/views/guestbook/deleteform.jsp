<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form action="<%=request.getContextPath() %>/guestbook" method="post" >
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="<%=no %>">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인">
				</form>
			<a href="<%=request.getContextPath() %>/guestbook"></a>
			</div>
		</div>
	</div>
</body>
</html>