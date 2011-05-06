<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/template/">Šablony</a></p>

<form method="post" action="${f:url('/admin/template/edit')}">
	<table>
		<tr>
			<th><label for="name">Jméno:</label></th>
			<td>
				<input type="text" id="name" ${f:text("name")} class="text ${f:errorClass('name', 'error')}" />
				${f:h(errors.name)}
			</td>
		</tr>
		<tr>
			<th><label for="content">Obsah:</label></th>
			<td>
				<textarea name="content" id="content" class="${f:errorClass('content', 'error')}">${f:h(content)}</textarea>
				${f:h(errors.content)}
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<input type="hidden" ${f:hidden("key")}/>
				<input type="hidden" ${f:hidden("version")}/>
				<input type="submit" name="submit" id="submit" class="default" value="Upravit" /> &nbsp;
				<a href="/admin/template/">Zpět</a>
			</td>
		</tr>
	</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>