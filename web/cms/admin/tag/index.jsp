<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/tag/add")}">Přidat štítek</a></p>

<c:if test="${empty tags}">
	<p class="notice">Nebyly přidány žádné štítky</p>
</c:if>
<c:if test="${not empty tags}">
	<table class="grid">
		<tr>
			<th>Jméno</th>
			<th>Akce</th>
		</tr>
		<c:forEach var="tag" items="${tags}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(tag.name)}</td>
				<td>
					<c:set var="editUrl" value="edit/${f:h(tag.key)}"/>
					<c:set var="deleteUrl" value="delete/${f:h(tag.key)}"/>
					<a href="${f:url(editUrl)}">Upravit</a> &nbsp;
					<a href="${f:url(deleteUrl)}">Smazat</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>