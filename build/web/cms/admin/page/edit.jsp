<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<ul>
<c:forEach var="error" items="${f:errors()}">
	<li>${f:h(error)}</li>
</c:forEach>
</ul>

<form method="post" action="${f:url('/admin/page/edit')}">
<table>
	<tr>
		<th><label for="url">Url:</label></th>
		<td>
			<input type="text" id="url" ${f:text("url")} class="${f:errorClass('url', 'error')}" />
			${f:h(errors.url)}
		</td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
			<input type="hidden" ${f:hidden("key")}/>
			<input name="submit" id="submit" value="Přidat" type="submit" />
		</td>
	</tr>
</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>