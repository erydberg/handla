<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Inköpslista ${shoplist.name }</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script>
var path = '${pageContext.request.contextPath}';
var listid = '${shoplist.listId}';
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
  $('.list').on("click",".edit", function(){
	  var articleId = $(this).data('id');
	  var articleText = $(this).prev().text().trim();
	 $(this).attr("src", path + "/css/close.png");
	 $(this).attr("class","close");
	 
	 //lägga till länk för ta bort
	 var delButton = "<a class='delbutton' href='"+ path +"/article/delete/"+ articleId +"/from/"+listid+"'><img src='" + path + "/css/delete.png'></a>";
	 $(delButton).insertAfter(this);
	 //lägga till funktion för edit
	 var editButton = "<img class='editbutton' data-id='"+articleId+"' data-text='"+articleText+"' src='" + path + "/css/edit3.png'>";
	 $(editButton).insertAfter(this); 
  });
  $('.list').on("click",".close",function(){
	  $(this).next('.editbutton').remove();
	  $(this).next('.delbutton').remove();
	  $(this).attr("src", path + "/css/edit2.png");
	  $(this).attr("class","edit");
	  $('#title').val('');
	  $('#id').val('');
  });
  $('.list').on("click",".editbutton",function(){
	  var text = $(this).data('text').trim();
	  var articleId = $(this).data('id');
	  $('#title').val(text);
	  $('#id').val(articleId);
	  $('#title').focus();
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
   function showEditButtons(articleId){
	   var delButton = "<a class='edittools' href='"+ path +"/article/delete/"+ articleId +"/from/"+listid+"'><img src='" + path + "/css/delete.png'></a>";
	   var closeButton = "<a class='edittools' href='javascript:closeEditMode("+ articleId + ");'><img src='" + path + "/css/close.png'></a>";
	   var editButton = "<a class='edittools' href='javascript:editArticle("+articleId+")'><img src='" + path + "/css/edit3.png'></a>";
	   $("#editbutton"+articleId).html(closeButton + editButton + delButton);
   }
   
   function closeEditMode(articleId){
	   var editModeButton = "<a class='edit' href='javascript:showEditButtons("+articleId+");''><img src='"+path+"/css/edit2.png'></a>";
	   $("#editbutton"+articleId).html(editModeButton);
   }
   
   function editArticle(articleId){
	  //get the text
	  
   }
   
</script>
</head>
<body>

<div class="nav-box">
<span class="toolbar">
<a class="toolitem" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/css/back.png"></a>
<a class="toolitem" href="${pageContext.request.contextPath}/shoplist/deleteboughtarticlesfromlistid/${shoplist.listId}" onClick="reloadPage();"><img src="${pageContext.request.contextPath}/css/list-delete.png"></a>
<a class="toolitem" href="#" onClick="reloadPage();"><img src="${pageContext.request.contextPath}/css/reload.png"></a>
</span>
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
	<li><form:checkbox path="articles[${counter.index}].bought" id="${article.id}"/><label for="${article.id}"> ${article.completeTitle}</label>
	<img class="edit" data-id="${article.id}" src="${pageContext.request.contextPath}/css/edit2.png"></li>	
</c:forEach>
</ul>

<div class="submit-area">
<a href="#" onClick="reloadPage();">Uppdatera listan</a> | <a href="${pageContext.request.contextPath}/">Tillbaka</a>
</div>
</form:form>
</body>
</html>