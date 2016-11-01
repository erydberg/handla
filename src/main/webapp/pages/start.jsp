<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Handla</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>

<script>
function confirmDelete(id){
	var r=confirm("Vill du ta bort listan?");
	if (r==true)
	  {
		window.location.href = '${pageContext.request.contextPath}/shoplist/delete/'+id;
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
<a class="toolitem" href="${pageContext.request.contextPath}/shoplist/new"><img src="${pageContext.request.contextPath}/css/newlist.png"></a>
</span>
<h1>Handla test igen tjoho</h1>
</div>
<div class="notepad">
<ul class="list">
<c:forEach items="${shoplists }" var="shoplist">
	<li><a href="${pageContext.request.contextPath}/shoplist/open/${shoplist.listId}">${shoplist.name }</a> <a href="#" onclick="return confirmDelete(${shoplist.listId});" class="del"><img src="${pageContext.request.contextPath}/css/delete.png"></a></li>
</c:forEach>
</ul>
</div>
<div>
<a href="${pageContext.request.contextPath}/user">Visa användare</a>
</div>
</body>
</html>