<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>PROPPS - Recherche membre</title>

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


	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="${pageContext.request.contextPath}/index.html">ProPPS</a>
				<div class="nav-collapse collapse navbar-responsive-collapse">
					<form action="result_recherche_membre.html"
						class="navbar-search pull-left">
						<input type="text" placeholder="Recherches"
							class="search-query span2">
					</form>
					<ul class="nav">
						<li><a href="#"><i class="icon-search"></i> Recherche
								Avancée</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-list-alt"></i>
								Invitations <span class="badge badge-info">2</span> </a>
							<ul class="dropdown-menu">
								<li>
									<div id="notification">
										<div id="notif_contact">
											Nom, prénom</br>
											<button class="btn btn-mini btn-success" type="button">Accepter</button>
											<button class="btn btn-mini btn-danger" type="button">Refuser</button>
										</div>
									</div>
								</li>
								<li class="divider"></li>
								<li>
									<div id="notification">
										<div id="notif_contact">
											Nom, prénom</br>
											<button class="btn btn-mini btn-success" type="button">Accepter</button>
											<button class="btn btn-mini btn-danger" type="button">Refuser</button>
										</div>
									</div>
								</li>
							</ul></li>
						<li class="divider-vertical"></li>
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-home"></i>
								John Doe <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="compte.html"><i class="icon-user"></i> Mon
										compte</a></li>
								<li><a href="parametre.html"><i class="icon-wrench"></i>
										Paramètre</a></li>
								<li class="divider"></li>
								<!--<li><a href="messagerie.html"><i class="icon-envelope"></i> Messagerie</a></li>-->
								<li><a href="#"><i class="icon-off"></i> Deconnexion</a></li>
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
						<img src="${pageContext.request.contextPath}/img/search.png" class="pull-left">
						<h2>Recherche de membre</h2>
					</div>

					<form action="result_recherche_membre.html">
						<fieldset>
							<legend>Selectionnez vos critères de recherche</legend>
							<div class="container">
								<div class="row">
									<div class="span3">
										<label class="control-label" for="inputNom">Nom</label> <input
											type="text" name="nom" id="inputNom" placeholder="">
									</div>
									<div class="span3">
										<label class="control-label" for="inputPrenom">Prénom</label>
										<input type="text" name="prenom" id="inputPreom"
											placeholder="">
									</div>
									<div class="span3">
										<label class="control-label" for="inputEmail">Email</label> <input
											type="text" name="email" id="inputEmail" placeholder="">
									</div>
									<div class="span3">
										<label class="control-label" for="inputSociete">Société</label>
										<input type="text" name="societe" id="inputSociete"
											placeholder="">
									</div>
								</div>
							</div>

						</fieldset>
						<button type="submit" class="btn">Recherche</button>
					</form>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Prénom</th>
								<th>Email</th>
								<th>Société</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="member" items="${requestScope.memberList}" > 
							<tr>
								<td>${member.sNom }</td>
								<td>${member.sPrenom }</td>
								<td>${member.sEmail }</td>
							</tr>
						</c:forEach>
						<!-- 
							<tr>
								<td><a href="user.html">Guilizzoni</a></td>
								<td>Giacomo</td>
								<td>giacomo.guilizzoni@microsoft.com</td>
								<td>Microsoft</td>
							</tr>
							<tr>
								<td><a href="#">Botton</a></td>
								<td>Marco</td>
								<td>marcob@aol.fr</td>
								<td>Amadeus</td>
							</tr>
							<tr>
								<td><a href="#">Maclachlan</a></td>
								<td>Mariah</td>
								<td>mmaclachlan@yahoo.com</td>
								<td>Dassaults</td>
							</tr>
							<tr>
								<td><a href="#">Liberty</a></td>
								<td>Valerie</td>
								<td>val.liberty@gmail.com</td>
								<td>-</td>
							</tr>
							-->
						</tbody>
					</table>

				</div>
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
