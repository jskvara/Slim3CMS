<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/author/">Autoři</a></p>

<form method="post" action="${f:url('/admin/author/add')}">
	<table>
		<tr>
			<th><label for="email">E-mail:</label></th>
			<td>
				<input type="text" id="email" ${f:text("email")} class="text ${f:errorClass('email', 'error')}" />
				${f:h(errors.email)}
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<input type="submit" name="submit" id="submit" class="default" value="Přidat" /> &nbsp;
				<a href="/admin/author/">Zpět</a>
			</td>
		</tr>
	</table>
</form>

<%@include file="/cms/admin/footer.jsp" %>