<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Rydberg - Logga in</title>
</head>
<body onload='document.f.j_username.focus();'>
	<form name="f" action="${pageContext.request.contextPath}/login" method="POST" class="form-general">
		<div class="form-box">
			<h3>Logga in</h3>

			<c:if test="${not empty error}">
				<div class="errorblock">${error }</div>
			</c:if>

			<label for="j_username">Användarnamn:</label> <input type="text"
				name="ssoId" id="username" placeholder="Ange användarnamn" required>
			<label for="j_password">Lösenord:</label> <input type='password'
				name='password' placeholder="Ange lösenord" required> <input
				type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</div>
		<input name="submit" type="submit" value="Logga in" />
	</form>
</body>
</html>