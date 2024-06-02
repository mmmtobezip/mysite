<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	   	<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${title }</td>
					</tr>
					<tr>
						<td class="label">작성자</td>
						<td>${userName }</td>
					</tr>
					<tr>
						<td class="label">작성일</td>
						<td>${regDate }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
										${fn:replace(fn:replace(fn:replace(contents, ">", "&gt;"), "<", "&lt;"), newline, "<br>") }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/board?a=list">글목록</a>
					
					<c:if test="${userNo == authUser.no }" >
							<a href="${pageContext.request.contextPath}/board?a=updateform&no=${no }">글수정</a>
					</c:if>
					
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board?a=replyform&no=${no }" id="new-book">답글작성</a>
					</c:if>
<%-- 					<c:choose>
						<c:when >
							<div class="bottom">
								<a href="${pageContext.request.contextPath}/board?a=updateform&no=${no }">글수정</a>
							</div>	
						</c:when>	
					</c:choose> --%>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>