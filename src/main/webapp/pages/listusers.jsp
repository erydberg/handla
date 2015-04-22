<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Användare</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>

<script>
function confirmDelete(id){
	var r=confirm("Vill du ta bort användaren?");
	if (r==true)
	  {
		window.location.href = '${pageContext.request.contextPath}/user/delete/'+id;
	  }
	else
	  {
	  return false;
	  }
	}
</script>
<body>

<div class="nav-box">
<span class="toolbar">
<a class="toolitem" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/css/back.png"></a>
<a class="toolitem" href="${pageContext.request.contextPath}/user/new"><img src="${pageContext.request.contextPath}/css/newuser.png"></a>
</span>
<h1>Användare</h1>
</div>
<div class="notepad">
<ul class="list">
<c:forEach items="${users }" var="user">
	<li><a href="${pageContext.request.contextPath}/user/open/${user.id}">${user.username }</a> <a href="#" onclick="return confirmDelete(${user.id});" class="del"><img src="${pageContext.request.contextPath}/css/delete.png"></a></li>
</c:forEach>
</ul>
</div>
<div><a href="${pageContext.request.contextPath}">Tillbaka</a></div>
</body>
</html>