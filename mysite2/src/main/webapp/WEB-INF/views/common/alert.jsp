<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>Alert</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp" />
    <div id="content">
        <div id="user">
            <c:choose>
                <c:when test="${alertType == 'writeFormNoti'}">
                    <script>
                        alert("제목을 입력해주세요.");
                        location.href = "${pageContext.request.contextPath}/board?a=writeform";
                    </script>
                </c:when>
                <c:when test="${alertType == 'updateSuccess'}">
                	<script>
                		alert("성공적으로 수정하였습니다.");
                		location.href = "${pageContext.request.contextPath}/board?a=view&no=${param.no}";
                	</script>
                </c:when>
            </c:choose>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp" />
    <c:import url="/WEB-INF/views/includes/footer.jsp" />
</div>
</body>
</html>