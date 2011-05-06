<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--@taglib prefix="f" uri="http://www.slim3.org/functions"--%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/news/add")}">Přidat novinku</a></p>

<c:if test="${empty news}">
	<p class="notice">Nebyly přidány žádné novinky</p>
</c:if>
<c:if test="${not empty news}">
	<table class="grid">
		<tr>
			<th>Nadpis</th>
			<th>Akce</th>
		</tr>
		<c:forEach var="item" items="${news}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(item.title)}</td>
				<td>
					<c:set var="editUrl" value="edit/${f:h(item.key)}"/>
					<c:set var="deleteUrl" value="delete/${f:h(item.key)}"/>
					<a href="${f:url(editUrl)}">Upravit</a> &nbsp;
					<a href="${f:url(deleteUrl)}">Smazat</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>