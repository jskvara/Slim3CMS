<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<form method="post" action="${f:url('/admin/page/add')}">
<table>
	<tr>
		<th><label for="url">Url:</label></th>
		<td>
			<input type="text" id="url" ${f:text("url")} class="text ${f:errorClass('url', 'error')}" />
			${f:h(errors.url)}
		</td>
	</tr>
	<tr>
		<th><label for="url">Titulek:</label></th>
		<td>
			<input type="text" id="title" ${f:text("title")} class="text ${f:errorClass('title', 'error')}" />
			${f:h(errors.title)}
		</td>
	</tr>
	<tr>
		<th><label for="url">Zobrazit:</label></th>
		<td>
			<input type="checkbox" id="visible" ${f:checkbox("visible")} class="text ${f:errorClass('visible', 'error')}" />
			${f:h(errors.visible)}
		</td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
			<input type="submit" name="submit" id="submit" class="default" value="Přidat" /> &nbsp;
			<a href="/admin/page/">Zpět</a>
		</td>
	</tr>
</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>