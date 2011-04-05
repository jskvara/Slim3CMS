<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@taglib prefix="s3ct" uri="/WEB-INF/tlds/slim3cmsTags.tld"%>
<%@include file="/cms/admin/header.jsp" %>

<p><a href="/admin/page/">Stránky</a></p>

<form method="post" action="${f:url('/admin/page/delete')}">
	<p>Opravdu si přejete smazat stránku: ${s3ct:entity("key")}</p>
	<p><input type="submit" name="submit" id="submit" value="Smazat" /></p>
</form>

<%@include file="/cms/admin/footer.jsp" %>