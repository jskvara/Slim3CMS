<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/news/">Novinky</a></p>

<form method="post" action="${f:url('/admin/news/edit')}">
	<table>
		<tr>
			<th><label for="title">Nadpis:</label></th>
			<td>
				<input type="text" id="title" ${f:text("title")} class="text ${f:errorClass('title', 'error')}" />
				${f:h(errors.title)}
			</td>
		</tr>
		<tr>
			<th><label for="text">Text:</label></th>
			<td>
				<textarea name="text" id="text" class="${f:errorClass('text', 'error')}">${f:h(text)}</textarea>
				${f:h(errors.text)}
			</td>
		</tr>
		<tr>
			<th><label for="created">Vytvořeno:</label></th>
			<td>
				<input type="text" id="created" ${f:text("created")} class="text ${f:errorClass('created', 'error')}" />
				${f:h(errors.created)}
			</td>
		</tr>
		<tr>
			<th><label for="visible">Zobrazit:</label></th>
			<td>
				<input type="checkbox" id="visible" ${f:checkbox("visible")} class="${f:errorClass('visible', 'error')}" />
				${f:h(errors.visible)}
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<input type="hidden" ${f:hidden("key")}/>
				<input type="hidden" ${f:hidden("version")}/>
				<input type="submit" name="submit" id="submit" class="default" value="Upravit" /> &nbsp;
				<a href="/admin/news/">Zpět</a>
			</td>
		</tr>
	</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>