<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<form method="post" action="${f:url('/admin/page/add')}">
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
			<input type="submit" name="submit" id="submit" value="Přidat" /> &nbsp;
			<a href="/admin/page/">Zpět</a>
		</td>
	</tr>
</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>