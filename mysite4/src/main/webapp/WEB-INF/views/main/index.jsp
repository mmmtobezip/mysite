<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html>
<html>
<head>
<title>${sitevo.title }</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	   <c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
				<!-- 자기 사진으로 바꾸기  assets/images/luffy.jpg" style='width: 250px'-->
					<!-- <img id="profile" src="${pageContext.request.contextPath}/assets/images/profile.jpg" style ='width: 250px'>  -->
					<img id="profile" src="${pageContext.request.contextPath }${sitevo.profile }" style="width:350px">
					<!-- <h2>안녕하세요. 박지현의  mysite에 오신 것을 환영합니다.</h2>  -->
					<h2> 
						${sitevo.welcome }
					</h2>
					<p>
						${fn:replace(sitevo.description, newline, "<br>") }
						<br><br>
						<a href="${pageContext.request.contextPath }/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>