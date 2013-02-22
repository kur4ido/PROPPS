<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>PROPPS - Inscription entreprise</title>
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

<% String email= (String) request.getAttribute("email"); %>
<% String nom= (String) request.getAttribute("nom"); %>
<% String prenom= (String) request.getAttribute("prenom"); %>
<% String ville= (String) request.getAttribute("ville"); %>
<% String adresse= (String) request.getAttribute("adresse"); %>
<% String codePostal= (String) request.getAttribute("codePostal"); %>
<% String pays= (String) request.getAttribute("pays"); %>
<% String NomSociete= (String) request.getAttribute("NomSociete"); %>
<% String errorMdp= (String) request.getAttribute("errorMdp"); %>
<% String errorMdpInvalide= (String) request.getAttribute("errorMdpInvalide"); %>
<% String errorMail= (String) request.getAttribute("errorMail"); %>

<body data-spy="scroll">

  <div id="header">
		<div class="container">
			<h1>ProPPS</h1>
		</div>
	</div>

	<div id="wrap">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="page-header">
						<h1>
							Réseau Professionnel Paris-Sud<small> Inscription
								entreprise</small>
						</h1>
					</div>
					<div id="contents">
						<p>
							En inscrivant votre entreprise sur notre site, vous aurez la
							possibilité de poster à  tout moment des informations à  propos de
							celle ci (évènements, offres d'emplois, etc...)<br> Afin
							d'inscrire votre entreprise sur ce site, il vous suffit de
							renseigner son nom et une adresse mail/compte utilisateur à 
							laquelle nous pourrons vous contacter.<br> Il vous est
							également possible d'ajouter toutes informations vous semblant
							utiles (domaine d'activté, localisation de l'entreprise,etc...)
						</p>

						<form class="form-horizontal" action="${pageContext.request.contextPath}/registRecruiter"
							method="post">
							<fieldset>
								<legend>Formulaire d'inscription</legend>
								<div class="control-group">
									<label class="control-label" for="inputNom">Nom</label>
									<div class="controls">
										<input type="text" name="inputNom" id="inputNom"
											placeholder="Nom" value=<%=nom %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPrenom">Prénom</label>
									<div class="controls">
										<input type="text" name="inputPrenom" id="inputPrenom"
											placeholder="Prénom" value=<%=prenom %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputEmail">Adresse
										e-mail</label>
									<div class="controls">
										<input type="text" id="inputEmail" name="inputEmail"
											placeholder="recrutement@polytech.fr" value=<%=email %>>
											<!-- Message d'erreur -->
											<c:if test="${request.getAttribute('errorMail')!=''}">
										<span class="text-error"><%=errorMail %></span>
										</c:if>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPassword">Mot de
										passe</label>
									<div class="controls">
										<input type="password" id="inputPassword" name="inputPassword"
											placeholder="Mot de passe"> <a href="#" rel="tooltip"
											data-placement="top"
											title="Le mot de passe doit contenir au minimum une majuscule 
									et un chiffre. Le mot de passe doit avoir une longueur minimum de 8 caractères"><i
											class="icon-info-sign"></i></a>
											<c:if test="${request.getAttribute('errorMdpInvalide')!=''}">
											<span class="text-error"><%=errorMdpInvalide %></span>
											</c:if>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputConfirmPassword">Confirmez
										le mot de passe</label>
									<div class="controls">
										<input type="password" id="inputConfirmPassword"
											name="inputConfirmPassword" placeholder="Mot de passe">
									</div>										
										<!-- Message d'erreur -->
									<c:if test="${request.getAttribute('errorMdp')!='' }" >
										<span class="text-error"><%=errorMdp %></span>
									</c:if>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputSociete">Nom de
										la société</label>
									<div class="controls">
										<input type="text" id="inputSociete" name="inputSociete"
											placeholder="Polytech" data-provide="typeahead" value=<%=NomSociete %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputAdressFact">Adresse
										de facturation</label>
									<div class="controls">
										<input type="text" id="inputAdressFact" name="inputAdressFact"
											placeholder="...." value=<%=adresse %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPays">Pays</label>
									<div class="controls">
										<input type="text" id="inputPays" name="inputPays"
											placeholder="France" value=<%=pays %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputCodePostal">Code
										postal</label>
									<div class="controls">
										<input type="text" id="inputCodePostal" name="inputCodePostal"
											placeholder="91305" value=<%=codePostal %>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputVille">Ville</label>
									<div class="controls">
										<input type="text" id="inputVille" name="inputVille"
											placeholder="Orsay" value=<%=ville %>>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn btn-primary">Inscription</button>
									</div>
								</div>
							</fieldset>
						</form>
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
	<script type="text/javascript">
		$(function() {
			$("[rel='tooltip']").tooltip();
		});
		var list = "${requestScope.stringList}";
		$("#inputSociete").typeahead({
			minLength : 2,
			source : list
		});
	</script>
</body>
</html>
