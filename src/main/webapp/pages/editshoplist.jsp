<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Lägg till eller ändra Lista</title>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
</head>
<body>

<div class="nav-box">
<h1>Lista</h1>
<form:form commandName="shoplist" method="post" action="${pageContext.request.contextPath}/shoplist/save" cssClass="form-general">
<form:hidden path="listId" id="listId"/>

<div class="form-box">
<fieldset>
<div class="text size-3">
<label>Namn på listan:</label>
<form:input path="name" id="name"/>
</div>
</fieldset>
<div class="submit-area">
<input type="submit" name="saveShoplist" value="Spara"/> | <a href="${pageContext.request.contextPath}">Avbryt</a>
</div>
</div>

</form:form>
</div>
<ul>
</ul>
<script type="text/javascript">
            document.getElementById('name').focus();
        </script>
</body>
</html>