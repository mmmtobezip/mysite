<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> <!-- sprig으로 시작하는 태그 라이브러리ㄴ --> 
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$("#btn-check").click(function() {
		var email = $("#email").val();
		if(email == '') {
			return;
		}
		
		$.ajax({
			url: "/mysite3/user/api/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			error: function(xhr, status, err){
				console.error(err);
			},
			success: function(response){
				if(response.exist) {
					alert("존재하는 이메일입니다. 다른 이메일을 사용해 주세요.")
					$("#email").val("");
					$("#email").focus();
					return;	
				}
				
				// 사용할 수 있는 이메일
				$("#btn-check").hide();
				$("#img-check").show();
			}
		});

	})
});

//$(f); //dom로딩 된 후 f 함수를 실행함. -> dom load event를 받는 function 
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
					<!--  절대경로 사용 -->
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">
					<p style="text-align:left; padding:0">
						<spring:hasBindErrors name="userVo">
							<c:if test='${errors.hasFieldErrors("name") }'>
								${errors.getFieldError("name").defaultMessage }
							</c:if>
						</spring:hasBindErrors>
					</p>

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id="btn-check" type="button" value="이메일 확인">
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="vertical-align:bottom; width:20px; display:none">
					
					<label class="block-label">
						<spring:message code="user.join.label.password" />
					</label>
						
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>