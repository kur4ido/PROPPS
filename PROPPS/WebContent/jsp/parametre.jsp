<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="icon" href="../img/propps.ico">
</head>

<body data-spy="scroll" data-target=".nav-list">
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
				<a class="brand" href="../index.html">ProPPS</a>
				<div class="nav-collapse collapse navbar-responsive-collapse">
					<form action="recherche_membre.html"
						class="navbar-search pull-left">
						<input type="text" placeholder="Recherches"
							class="search-query span2">
					</form>

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
								John Doe <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="compte.html"><i class="icon-user"></i> Mon
										compte</a></li>
								<li><a href="messagerie.html"><i class="icon-inbox"></i>
										Inbox</a></li>
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

	<div id="wrap">

		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="page-header">
						<h2>Paramètres généraux du compte</h2>
					</div>


					<form id="modificationIdentite" class="form-horizontal" action="">
						<fieldset>
							<legend>Modification des informations personnelles</legend>
							<div class="control-group">
								<label class="control-label" for="inputNom">Nom</label>
								<div class="controls">
									<input type="text" name="nom" id="inputNom" placeholder="Doe">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPrenom">Prénom</label>
								<div class="controls">
									<input type="text" name="prenom" id="inputPrenom"
										placeholder="John">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputVille">Ville</label>
								<div class="controls">
									<input type="text" name="ville" id="inputVille"
										placeholder="Orsay">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputPhoto">Photo de
									profil</label>
								<div class="controls">
									<input type="text" name="photoProfil" id="inputPhoto"
										placeholder="Orsay">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">Valider</button>
								</div>
							</div>
						</fieldset>
					</form>
					<!-- Voir à modifier peut-être le nom du formulaire pour qu'il soit plus explicite -->
					<form id="modificationProfil" class="form-horizontal" action="">
						<fieldset>
							<legend>Modification des informations de connexion du
								compte</legend>
							<div class="control-group">
								<label class="control-label" for="inputEmail">Adresse
									e-mail</label>
								<div class="controls">
									<input type="email" name="email" id="inputEmail"
										placeholder="johndoe@yahoo.fr">
								</div>

							</div>
							<div class="control-group">
								<label class="control-label" for="inputPassword">Mot de
									passe</label>
								<div class="controls">
									<input type="password" name="password" id="inputPassword"
										placeholder="Password"> <a href="#" rel="tooltip"
										data-placement="right"
										title="Le mot de passe doit contenir au minimum une majuscule 
									et un chiffre. Le mot de passe doit avoir une longueur minimum de 8 caractères"><i
										class="icon-info-sign"></i></a>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputConfirmPassword">Confirmez
									le mot de passe</label>
								<div class="controls">
									<input type="password" name="confirmPassword"
										id="inputConfirmPassword" placeholder="Password">
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">Valider</button>
								</div>
							</div>

						</fieldset>
					</form>
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
		<script src="../js/jquery-1.9.0.js"></script>
		<script src="../js/bootstrap.js"></script>
		<script src="../js/bootstrap.min.js"></script>

		<script>
			$('.nav-list').scrollspy();
			$(document).off('touchstart.dropdown.data-api');
			$('.dropdown-toggle').dropdown();
			$(function() {
				$("[rel='tooltip']").tooltip();
			});
		</script>
</body>
</html>
