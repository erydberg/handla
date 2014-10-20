<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Inköpslista ${shoplist.name }</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
<script>
var path = '${pageContext.request.contextPath}';
$(document).ready(function () {
  $('input:checkbox').change(function () {
      if ($(this).prop("checked")) {
        //do the stuff that you would do when 'checked'
		var idvalue = $(this).prop('id');
		markBoughtArticle(idvalue,'true');
        return;
    }
    //Here do the stuff you want to do when 'unchecked'
	var idvalue = $(this).prop('id');
		markBoughtArticle(idvalue,'false');
   });
  if(!window.Touch){
  	$("#title").focus();
  }
 });
 
   function markBoughtArticle(id, bought){
    jQuery.ajax({
     url    : path + '/article/markbought/'+id+'/' + bought,
     type   : 'PUT',

     success : function(data){
              //You handle the response here like displaying in required div etc. 
               },
      error : function(errorData){
              alert("Det gick inte att uppdatera markeringen");
              }


    });
}
   function reloadPage(){
	   window.location = window.location.pathname;
   }
</script>
</head>
<body>

<div class="nav-box">
<h1>${shoplist.name }</h1>
</div>
<div>
<form:form commandName="article" method="post" action="${pageContext.request.contextPath}/article/addtoshoplist/${shoplist.listId }" cssClass="addArticle">
<form:hidden path="id" id="id"/>
<form:input path="title" id="title"/>
<input type="submit" name="saveArticle" value="köp"/>
</form:form>
</div>

<form:form method="post" action="${pageContext.request.contextPath}/shoplist/reload/" modelAttribute="shoplist" cssClass="notepad">
<ul class="list">
<c:forEach items="${shoplist.articles }" var="article" varStatus="counter">
	<li><form:checkbox path="articles[${counter.index}].bought" id="${article.id}"/><label for="${article.id}"> ${article.title }</label> <a href="${pageContext.request.contextPath}/article/delete/${article.id}/from/${shoplist.listId}" class="del"><img src="${pageContext.request.contextPath}/css/delete.png"></a></li>	
</c:forEach>
</ul>

<div class="submit-area">
<a href="#" onClick="reloadPage();">Uppdatera listan</a> | <a href="${pageContext.request.contextPath}">Tillbaka</a>
</div>
</form:form>
</body>
</html>