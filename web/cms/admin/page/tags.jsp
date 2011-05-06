<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<c:if test="${empty tags}">
	<p class="notice">Nebyly přidány žádné štítky. <a href="/admin/page/">Zpět</a></p>
</c:if>
<c:if test="${not empty tags}">
	<form method="post" action="${f:url('/admin/page/tags')}">
		<table>
			<tr>
				<th><label for="tagArray">Štítky:</label></th>
				<td>
					<select name="tagArray" id="tagArray" multiple="multiple" size="5" class="${f:errorClass('tagArray', 'error')}">
						<c:forEach var="tag" items="${tags}">
							<option ${f:multiselect("tagArray", tag.name)}>${tag.name}</option>
						</c:forEach>
					</select>
					${f:h(errors.tagArray)}
				</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td>
					<input type="hidden" ${f:hidden("key")}/>
					<input type="submit" name="submit" id="submit" class="default" value="Nastavit" /> &nbsp;
					<a href="/admin/page/">Zpět</a>
				</td>
			</tr>
		</table>
	</form>
</c:if>

<%@include file="/cms/admin/footer.jsp" %>