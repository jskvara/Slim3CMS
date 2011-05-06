<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s3ct" uri="/WEB-INF/tlds/slim3cmsTags.tld"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><c:if test="${not empty pageTitle}">${pageTitle} | </c:if>Slim3 CMS</title>

	<link rel="stylesheet" href="/css/blueprint/screen.css" type="text/css" media="screen, projection, tv" />
	<link rel="stylesheet" href="/css/blueprint/print.css" type="text/css" media="print" />
	<!--[if lt IE 8]><link rel="stylesheet" href="/css/blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
	<link rel="stylesheet" href="/css/admin.css" type="text/css" media="screen, projection, tv" />
</head>
<body>

	<div id="wrapper" class="container"><!-- #wrapper -->

		<div id="header"><!-- #header -->
			<div class="span-12">
				<a href="/">Stránka</a> &nbsp; | &nbsp;
				<a href="/admin/">Administrace</a>
			</div>
			<div id="userbox" class="span-12 last">
				<p>
					<strong>${username}</strong> |
					<a href="${logoutUrl}">odhlásit</a>
				</p>
			</div>
		</div><!-- /#header -->
		<hr />

		<div id="sidebar" class="span-4"><!-- #sidebar -->
			<br /><br /><br />
			<ul>
				<li><a href="/admin/page/">Stránky</a></li>
				<li><a href="/admin/news/">Novinky</a></li>
				<li><a href="/admin/tag/">Štítky</a></li>
				<li><a href="/admin/template/">Šablony</a></li>
				<li><a href="/admin/author/">Autoři</a></li>
			</ul>
		</div><!-- /#sidebar -->

		<div id="content" class="span-20 last"><!-- #content -->
			<h1><c:if test="${not empty pageTitle}">${pageTitle}</c:if><c:if test="${empty pageTitle}">Administrace</c:if></h1>

			<c:forEach var="msg" items="${s3ct:getMessages()}">
				<div class="${f:h(msg.type)}">${f:h(msg.message)}</div>
			</c:forEach>