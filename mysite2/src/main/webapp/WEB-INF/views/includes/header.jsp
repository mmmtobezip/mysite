<%@page import="com.poscodx.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
	<div id="header">
		<h1>MySite</h1>
			<ul>
				<c:choose>
					<c:when test='${empty authUser}' >
						<!--  로그인 전, 따라서 session이 null일 수 있는 케이스 -->
						<li><a href="${pageContext.request.contextPath}/user?a=loginform">로그인</a><li>
						<li><a href="${pageContext.request.contextPath}/user?a=joinform">회원가입</a><li></li>
					</c:when>
					
					<c:otherwise>
						<!-- 로그인이 되어 있어서 null이 들어갈 수 없는 케이스 -->
						<li><a href="${pageContext.request.contextPath}/user?a=updateform">회원정보수정</a><li>
						<li><a href="${pageContext.request.contextPath}/user?a=logout">로그아웃</a><li>
						<li>${authUser.name }님 안녕하세요 ^^;</li>
					</c:otherwise>
				</c:choose>

			</ul>
	</div>