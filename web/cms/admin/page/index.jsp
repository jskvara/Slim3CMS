<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/page/add")}">Přidat stránku</a></p>

<c:if test="${empty pages}">
	<p class="notice">Nebyly přidány žádné stránky</p>
</c:if>
<c:if test="${not empty pages}">
	<table class="grid">
		<tr>
			<th>Url</th>
			<th>Akce</th>
		</tr>
		<c:forEach var="page" items="${pages}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>/${f:h(page.url)}</td>
				<td>
					<%-- jsp uses + for mathematical operation --%>
					<c:set var="tagsUrl" value="tags/${f:h(page.key)}"/>
					<c:set var="editUrl" value="edit/${f:h(page.key)}"/>
					<c:set var="deleteUrl" value="delete/${f:h(page.key)}"/>
					<a href="${f:url(tagsUrl)}">Štítky</a> &nbsp;
					<a href="${f:url(editUrl)}">Upravit</a> &nbsp;
					<a href="${f:url(deleteUrl)}">Smazat</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>