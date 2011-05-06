<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="${f:url("/admin/template/add")}">Přidat šablonu</a></p>

<c:if test="${empty templates}">
	<p class="notice">Nebyly přidány žádné šablony</p>
</c:if>
<c:if test="${not empty templates}">
	<table class="grid">
		<tr>
			<th>Jméno</th>
			<th>Akce</th>
		</tr>
		<c:forEach var="template" items="${templates}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(template.name)}</td>
				<td>
					<c:set var="editUrl" value="edit/${f:h(template.key)}"/>
					<c:set var="deleteUrl" value="delete/${f:h(template.key)}"/>
					<a href="${f:url(editUrl)}">Upravit</a> &nbsp;
					<a href="${f:url(deleteUrl)}">Smazat</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>