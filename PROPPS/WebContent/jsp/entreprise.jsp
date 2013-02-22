<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>PROPPS - Profil entreprise</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/datepicker.css" rel="stylesheet">
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
<% String nom= (String) request.getAttribute("nom"); %>
<% String prenom= (String) request.getAttribute("prenom"); %>
<% String ville= (String) request.getAttribute("ville"); %>
<% String adresse= (String) request.getAttribute("adresse"); %>
<% String codePostal= (String) request.getAttribute("codePostal"); %>
<% String NomSociete= (String) request.getAttribute("NomSociete"); %>


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




					<ul class="nav pull-right">

						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-home"></i>
								<%=NomSociete %> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="entreprise.html"><i class="icon-user"></i>
										Mon compte</a></li>
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

	<div id="header-compte">

		<div class="container">
			<div class="row">
				<div class="span12">
					<div id="fiche_entreprise">
						<img src="${pageContext.request.contextPath}/img/entreprise.jpg" class="img-polaroid">
						<div id="detail_entreprise">

							<h2><%=NomSociete %></h2>
							<h3><%=nom %>, <%=prenom %></h3>
							<br>
							<p class="lead">
								<i class="icon-home"></i> <%=adresse %><br> <%=ville %>, <%=codePostal %><br>	
							</p>
						</div>
					</div>
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
						<h2>Fonction de recrutement</h2>
					</div>
					<form class="form-horizontal"
						action="result_recherche_entreprise.html">
						<fieldset>
							<div class="control-group">
								<label class="control-label" for="expertise"> Domaines
									d'intervention </label>
								<div class="controls">
									<label class="checkbox"> <input type="checkbox"
										name="expertise1" value="EmissionFront" checked>
										Emission - Front office
									</label> <label class="checkbox"> <input type="checkbox"
										name="expertise2" value="EmissionBack"> Emission -
										Back office
									</label> <label class="checkbox"> <input type="checkbox"
										name="expertise3" value="CommercantFront"> CommerÃ§ant
										- Front office
									</label> <label class="checkbox"> <input type="checkbox"
										name="expertise4" value="CommercantBack"> CommerÃ§ant -
										Back office
									</label> <label class="checkbox"> <input type="checkbox"
										name="expertise5" value="AutomatesFront"> Automates -
										Front office
									</label> <label class="checkbox"> <input type="checkbox"
										name="expertise6" value="AutomatesBack"> Automates -
										Back office
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="profil"> Profil </label>
								<div class="controls">
									<label class="radio"> <input type="radio" name="profil"
										id="optionsRadios1" value="MOA" checked> MOA
									</label> <label class="radio"> <input type="radio"
										name="profil" id="optionsRadios2" value="MOE"> MOE
									</label> <label class="radio"> <input type="radio"
										name="profil" id="optionsRadios3" value="DCompetence">
										Double Compétence
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="experience">
									Expérience minimum requise : (années) </label>
								<div class="controls">
									<select class="span1" name="experience">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="mode"> Choix du mode
									de recrutement </label>
								<div class="controls">
									<label class="radio"> <input type="radio" name="mode"
										id="optionsRadios1" value="prestation" checked>
										Prestation
									</label> <label class="radio"> <input type="radio" name="mode"
										id="optionsRadios2" value="cdi"> Contrat permanent
									</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="dateDebut">Date de
									début</label>
								<div class="controls">
									<div class="input-append date" id="dp3" data-date="04/02/2013"
										data-date-format="dd/mm/yyyy">
										<input class="span2" size="16" type="text" value="04/02/2013">
										<span class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="dateFin">Date de fin</label>
								<div class="controls">
									<div class="input-append date" id="dp3" data-date="04/02/2013"
										data-date-format="dd/mm/yyyy">
										<input class="span2" size="16" type="text" value="04/02/2013">
										<span class="add-on"><i class="icon-calendar"></i></span>
										
									</div>
									<a href="#" rel="tooltip" data-placement="right"
										title="Ne renseigner une date que si vous recherchez un poste de type : Prestation"><i
										class="icon-info-sign"></i></a>
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">Lancer
										la recherche</button>
								</div>
							</div>
						</fieldset>
					</form>
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
	<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
	<script>
		$('.date').datepicker();
		$(document).off('touchstart.dropdown.data-api');
		$('.dropdown-toggle').dropdown();
	</script>
	<script type="text/javascript">
		$(function() {
			$("[rel='tooltip']").tooltip();
		});
	</script>
</body>
</html>
