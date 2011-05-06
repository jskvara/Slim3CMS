<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<form method="post" action="${f:url('/admin/page/edit')}">
<table>
	<tr>
		<th><label for="url">Url:</label></th>
		<td>
			/ <input type="text" id="url" ${f:text("url")} class="text ${f:errorClass('url', 'error')}" />
			${f:h(errors.url)}
		</td>
	</tr>
	<tr>
		<th><label for="title">Titulek:</label></th>
		<td>
			<input type="text" id="title" ${f:text("title")} class="text ${f:errorClass('title', 'error')}" />
			${f:h(errors.title)}
		</td>
	</tr>
	<tr>
		<th><label for="content">Obsah:</label></th>
		<td>
			<textarea cols="40" rows="10" name="content" id="content" class="${f:errorClass('content', 'error')}">${f:h(content)}</textarea>
			${f:h(errors.content)}
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
		<th><label for="templateName">Šablona:</label></th>
		<td>
			<select name="templateName" id="template" class="${f:errorClass('templateName', 'error')}">
				<option ${f:select("templateName", "")}> - </option>
			<c:forEach var="tpl" items="${templates}">
				<option ${f:select("templateName", tpl.name)}>${tpl.name}</option>
			</c:forEach>
			</select>
			${f:h(errors.templateName)}
		</td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
			<input type="hidden" ${f:hidden("key")}/>
			<input type="hidden" ${f:hidden("version")}/>
			<input type="submit" name="submit" id="submit" class="default" value="Upravit" /> &nbsp;
			<a href="/admin/page/">Zpět</a>
		</td>
	</tr>
</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>