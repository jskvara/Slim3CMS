<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--@taglib prefix="f" uri="http://www.slim3.org/functions"--%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/page/add")}">Přidat stránku</a></p>

<c:if test="${empty pages}">
	<p>Nebyly přidány žádné stránky</p>
</c:if>
<c:if test="${not empty pages}">
	<c:forEach var="page" items="${pages}">
		<c:set var="editUrl" value="edit/${f:h(page.key)}"/><%-- jsp uses + for mathematical operation --%>
		<c:set var="deleteUrl" value="delete/${f:h(page.key)}"/>
		${f:h(page.url)}: 
			<a href="${f:url(editUrl)}">Upravit</a>
			<a href="${f:url(deleteUrl)}">Delete</a><br />
		<%--<form action="?action=edit" method="post">
			<input type="text" name="text" value="<c:out value="${todo.text}" />" />
			<input type="hidden" name="id" value="${todo.keyAsString}" />
			<input type="submit" name="submit" value="Edit" />
			<a href="?action=delete&id=${todo.keyAsString}">X</a>
		</form>--%>
	</c:forEach>
</c:if>
<%@include file="/cms/admin/footer.jsp" %>