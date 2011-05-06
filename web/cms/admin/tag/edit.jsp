<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/tag/">Štítky</a></p>

<form method="post" action="${f:url('/admin/tag/edit')}">
	<table>
		<tr>
			<th><label for="name">Jméno:</label></th>
			<td>
				<input type="text" id="name" ${f:text("name")} class="text ${f:errorClass('name', 'error')}" />
				${f:h(errors.name)}
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<input type="hidden" ${f:hidden("key")}/>
				<input type="hidden" ${f:hidden("version")}/>
				<input type="submit" name="submit" id="submit" class="default" value="Upravit" /> &nbsp;
				<a href="/admin/tag/">Zpět</a>
			</td>
		</tr>
	</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>