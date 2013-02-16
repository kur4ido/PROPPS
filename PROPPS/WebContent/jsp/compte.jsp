<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>PROPPS - John Doe</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>ageContext.request.contextPath}
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
<% String ville= (String) request.getAttribute("ville"); %>

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
						<li><a href="recherche_membre.html"><i
								class="icon-search"></i> Recherche Avancée</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-list-alt"></i>
								Invitations <span class="badge badge-info">2</span> </b></a>
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
								<%=nom %> <%=prenom %> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="compte.html"><i class="icon-user"></i> Mon
										compte</a></li>
								<li><a href="#"><i class="icon-inbox"></i> Inbox</a></li>
								<li><a href="parametre.html"><i class="icon-wrench"></i>
										Paramètres</a></li>
								<li class="divider"></li>
								<!--<li><a href="messagerie.html"><i class="icon-envelope"></i> Messagerie</a></li>-->
								<li><a href="#"><i class="icon-off"></i> Deconnexion</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<div id="header-compte">
		<div class="container">
			<div class="row">
				<div class="span12">

					<div id="fiche_user">
						<img src="${pageContext.request.contextPath}/img/oneil.jpeg" height="140" class="img-polaroid">
						<div id="detail_user">
							<h2><%=prenom %> <%=nom %></h2>
							<br>
							<p class="lead">
								<i class="icon-briefcase"></i> Poste (profil), Entreprise<br>
								<i class="icon-calendar"></i> Disponibilité <br> <i
									class="icon-home"></i> <%=ville %>

							</p>
							<a href="parametre.html#profil" class="btn btn-primary btn-small"
								type="button"><i class="icon-plus icon-white"></i> Editer
								mon profil</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="wrap">
		<div class="container">
			<div class="row">
				<div class="span9">
					<div id="parcours">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/experience.png">
							<h3>Expériences professionnelles</h3>
						</div>

						<div class="row">
							<div class="span2">
								<p>
								<h4>Chef de projet (profil)</h4>
								Dassault
								</p>
								<p class="muted">2008 - Aujourd'hui (4 ans)</p>
								<p class="muted">CDI</p>
								<p>
									<span class="label label-info">Emission - Back Office</span>
								</p>
							</div>
							<div class="span7">

								<p>
									Lorem ipsum dolor <br> <br>sit amet, consectetur
									adipiscing elit. Maecenas nec rhoncus mi. Donec nec enim
									lectus. Donec est ipsum, luctus nec eleifend ut, mattis
									fringilla arcu. Fusce placerat pretium elit eget egestas. In
									ultricies dictum erat non hendrerit. Nunc convallis feugiat
									ante, eget lacinia tellus aliquet et. Nulla mollis accumsan
									metus, non semper risus commodo sed. Nulla ornare mauris sit
									amet ligula hendrerit volutpat. Praesent mollis justo vitae
									ante tempor iaculis. Praesent eget turpis diam, a volutpat
									ipsum. Nunc id dapibus tortor. Maecenas pharetra ornare
									condimentum. Phasellus venenatis sodales justo vehicula
									laoreet. Morbi sapien nunc, blandit et imperdiet non, tincidunt
									et nulla. Donec consectetur, erat sed viverra fringilla, leo
									libero ultrices erat, sit amet tincidunt arcu lorem at nunc.
									Praesent euismod lacinia fermentum. Maecenas dictum interdum
									lectus convallis luctus. Aliquam erat volutpat. Donec a
									vulputate enim. Duis feugiat dignissim nisl a placerat. Sed
									purus felis, volutpat laoreet pretium vitae, feugiat at justo.
									Fusce viverra risus ut enim malesuada id porttitor elit tempus.
									Sed tellus elit, sodales at facilisis in, scelerisque sed orci.
								</p>

							</div>

						</div>

						<div class="row">
							<div class="span2">
								<p>
								<h4>Developpeur (profil)</h4>
								Amadeus
								</p>
								<p class="muted">2002 - 2008 (6 ans)</p> 
								<p class="muted">Prestation</p>
								<p>
									<span class="label label-info">Emission - Back Office</span>
								</p>
							</div>
							<div class="span7">
								<p>Description du poste</p>

							</div>

						</div>
						<hr class="bs-docs-separator">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/expertise.png">
							<h3>Domaines d'expertise</h3>
						</div>
						<div class="expertise">
							<span class="label label-info">Emission - Back Office</span> <span
								class="label label-info">CommerÃ§ant - Front Office</span> <span
								class="label label-info">Automates - Front Office</span>
						</div>
					</div>
				</div>
				<div class="span3">

					<div id="last-add">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/contact.png">
							<h3>Derniers ajouts</h3>
						</div>
						<div id="contact">
							<img src="${pageContext.request.contextPath}/img/people.png" width="64">
							<div id="description_contact">
								Contact 1<br> Profession, Entreprise
							</div>
						</div>

						<div id="contact">
							<img src="${pageContext.request.contextPath}/img/people.png" width="64">
							<div id="description_contact">
								Contact 2<br> Profession, Entreprise
							</div>
						</div>

						<div id="contact">
							<img src="${pageContext.request.contextPath}/img/people.png" width="64">
							<div id="description_contact">
								Contact 3<br> Profession, Entreprise
							</div>
						</div>
					</div>
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
