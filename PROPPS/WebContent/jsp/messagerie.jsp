<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>PROPPS - Messagerie</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="icon" href="${pageContext.request.contextPath}/img/propps.ico">
</head>

<body>
  <!-- Navbar
    ================================================== -->
<% String prenom= (String) request.getAttribute("prenom"); %>
<% String nom= (String) request.getAttribute("nom"); %>
<% String nomSociete= (String) request.getAttribute("nomSociete"); %>
<% String ID_Membre_Courant = (String) request.getAttribute("ID_Membre_Courant"); %>


	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="${pageContext.request.contextPath}/index.html">
				</a>
				<div class="nav-collapse collapse navbar-responsive-collapse">
					<form action="recherche_membre.html"
						class="navbar-search pull-left">
						<input type="text" placeholder="Recherches"
							class="search-query span2">
					</form>

					<ul class="nav pull-right">
						<li class="divider-vertical"></li>
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-home"></i>
								
							<c:if test="${not empty requestScope.nomSociete}"> <%=nomSociete %></c:if>
							<c:if test="${empty requestScope.nomSociete}"> <%=prenom %> <%=nom %></c:if>
							<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/seeCurrentUserProfile?ID_Membre_Courant=<%=ID_Membre_Courant %>" ></i> Mon
										compte</a></li>
								<li><a href="#"><i class="icon-inbox"></i> Inbox</a></li>
								<li><a href="${pageContext.request.contextPath}/modifPersonalInfo?ID_Membre_Courant=<%=ID_Membre_Courant %>"><i class="icon-wrench"></i>
										Paramètres</a></li>
								<li class="divider"></li>
								<li><a href="${pageContext.request.contextPath}/getMessages?ID_Membre_Courant=<%=ID_Membre_Courant %>"><i class="icon-envelope"></i> Messagerie</a></li>
								<li><a href="${pageContext.request.contextPath}/index.html"><i class="icon-off"></i> Deconnexion</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div id="wrap">

		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="page-header">
						<img src="${pageContext.request.contextPath}/img/inbox2.png" class="pull-left">
						<h2>Messagerie</h2>
					</div>
				</div>
			</div>
			<c:forEach items="${requestScope.messString}" var="entry">
				<div class="row">
					<div class="span1"><b> ${entry.value} </b></div>
					<div class="span11"><p> ${entry.key} </p></div>
				</div>
			</c:forEach>
<!-- 				<div class="span1"><b>17/02/2013</b></div> -->
<!-- 				<div class="span11"><p>Lorem ipsum dolor sit amet, consectetur -->
<!-- 					adipiscing elit. Donec molestie magna eu mi ultricies posuere. In -->
<!-- 					imperdiet mattis rutrum. Cum sociis natoque penatibus et magnis dis -->
<!-- 					parturient montes, nascetur ridiculus mus. Ut nec nibh et tellus -->
<!-- 					laoreet lobortis. Fusce lacus ligula, placerat eget commodo mattis, -->
<!-- 					faucibus sodales nisl. Pellentesque habitant morbi tristique -->
<!-- 					senectus et netus et malesuada fames ac turpis egestas. Nullam -->
<!-- 					blandit vulputate nisl vitae consequat. Sed sit amet purus sit amet -->
<!-- 					neque molestie aliquam et vitae nunc. Nam sit amet molestie velit. -->
<!-- 					Curabitur magna lacus, consequat sit amet faucibus et, commodo et -->
<!-- 					nulla. Pellentesque at augue id tellus semper volutpat. Nam -->
<!-- 					fermentum aliquam nulla, a posuere odio interdum feugiat. Donec -->
<!-- 					auctor magna eget diam molestie venenatis. Ut feugiat mauris id mi -->
<!-- 					laoreet et aliquam massa gravida. Integer lacus metus, sollicitudin -->
<!-- 					in adipiscing non, eleifend sed nisl. Suspendisse sed sem et massa -->
<!-- 					elementum viverra quis sed enim.</p></div> -->
<!-- 			</div> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="span1"><b>16/02/2013</b></div> -->
<!-- 				<div class="span11"><p>Lorem ipsum dolor sit amet, consectetur -->
<!-- 					adipiscing elit. Donec molestie magna eu mi ultricies posuere. In -->
<!-- 					imperdiet mattis rutrum. Cum sociis natoque penatibus et magnis dis -->
<!-- 					parturient montes, nascetur ridiculus mus. Ut nec nibh et tellus -->
<!-- 					laoreet lobortis. Fusce lacus ligula, placerat eget commodo mattis, -->
<!-- 					faucibus sodales nisl. Pellentesque habitant morbi tristique -->
<!-- 					senectus et netus et malesuada fames ac turpis egestas. Nullam -->
<!-- 					blandit vulputate nisl vitae consequat. Sed sit amet purus sit amet -->
<!-- 					neque molestie aliquam et vitae nunc. Nam sit amet molestie velit. -->
<!-- 					Curabitur magna lacus, consequat sit amet faucibus et, commodo et -->
<!-- 					nulla. Pellentesque at augue id tellus semper volutpat. Nam -->
<!-- 					fermentum aliquam nulla, a posuere odio interdum feugiat. Donec -->
<!-- 					auctor magna eget diam molestie venenatis. Ut feugiat mauris id mi -->
<!-- 					laoreet et aliquam massa gravida. Integer lacus metus, sollicitudin -->
<!-- 					in adipiscing non, eleifend sed nisl. Suspendisse sed sem et massa -->
<!-- 					elementum viverra quis sed enim.</p></div> -->
<!-- 			</div> -->
		</div>
	</div>
	</div>
	<div id="footer">
		<div class="container">
			<p class="muted credit">&copy;PROPPS 2013</p>
		</div>
	</div>

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://platform.twitter.com/widgets.js"
		type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.0.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

	<script>
		$(document).off('touchstart.dropdown.data-api');
		$('.dropdown-toggle').dropdown();
	</script>
</body>
</html>
