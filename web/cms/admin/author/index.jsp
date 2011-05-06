<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/author/add")}">Přidat autora</a></p>

<c:if test="${empty authors}">
	<p class="notice">Nebyli přidáni žádní autoři</p>
</c:if>
<c:if test="${not empty authors}">
	<table class="grid">
		<tr>
			<th>E-mail</th>
			<th>Akce</th>
		</tr>
		<c:forEach var="author" items="${authors}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(author.email)}</td>
				<td>
					<c:set var="editUrl" value="edit/${f:h(author.key)}"/>
					<c:set var="deleteUrl" value="delete/${f:h(author.key)}"/>
					<a href="${f:url(editUrl)}">Upravit</a> &nbsp;
					<a href="${f:url(deleteUrl)}">Smazat</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>