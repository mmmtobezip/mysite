<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath}"><spring:message code="navigation.li.main"/></a></li>
		<li><a href="${pageContext.request.contextPath}/guestbook"><spring:message code="navigation.li.guestbook"/></a></li>
		<li><a href="${pageContext.request.contextPath}/board"><spring:message code="navigation.li.board"/></a></li>
	</ul>
</div>