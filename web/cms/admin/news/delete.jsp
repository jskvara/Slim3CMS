<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@taglib prefix="s3ct" uri="/WEB-INF/tlds/slim3cmsTags.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/news/">Novinky</a></p>

<form method="post" action='${f:url("/admin/news/delete")}'>
	<p class="notice">Opravdu si přejete smazat novinku: &quot;${f:h(entity.title)}&quot;?</p>
	<p>
		<input type="hidden" ${f:hidden("key")}/>
		<input type="hidden" ${f:hidden("version")}/>
		<input type="submit" name="submit" id="submit" class="default" value="Smazat" /> &nbsp;
		<a href="/admin/news/">Zpět</a>
	</p>
</form>

<%@include file="/cms/admin/footer.jsp" %>