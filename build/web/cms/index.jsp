<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--@taglib prefix="f" uri="http://www.slim3.org/functions"--%>
<%@taglib prefix="f" uri="org.slim3.jsp/functions"%>
<html>
<head>

</head>
<body>
<form method="post" action="?action=new">
	New page
	<input type="text" name="url" id="url" />
	<input name="submit" id="submit" value="Add" type="submit" />
</form>
<hr />

<c:if test="${empty pages}">
	<p>No pages</p>
</c:if>
<c:if test="${not empty pages}">
	<c:forEach var="page" items="${pages}">
		${f:h(page.url)}<br />
		<%--<form action="?action=edit" method="post">
			<input type="text" name="text" value="<c:out value="${todo.text}" />" />
			<input type="hidden" name="id" value="${todo.keyAsString}" />
			<input type="submit" name="submit" value="Edit" />
			<a href="?action=delete&id=${todo.keyAsString}">X</a>
		</form>--%>
	</c:forEach>
</c:if>
</body>
</html>
