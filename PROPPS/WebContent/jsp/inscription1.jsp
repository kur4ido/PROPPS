<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>PROPPS - Inscription membre</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/datepicker.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.css"
	rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="icon"
	href="${pageContext.request.contextPath}/img/propps.ico">
</head>
<%
	String prenom = (String) request.getAttribute("prenom");
%>
<%
	String nom = (String) request.getAttribute("nom");
%>
<%
	String email = (String) request.getAttribute("email");
%>
<%
	String ville = (String) request.getAttribute("ville");
%>
<%
	String errorMdp = (String) request.getAttribute("errorMdp");
%>
<%
	String errorMail = (String) request.getAttribute("errorMail");
%>
<%
	String errorMdpInvalide = (String) request
			.getAttribute("errorMdpInvalide");
%>

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
							Réseau Professionnel Polytech Paris-Sud<small>
								Inscription membre</small>
						</h1>
					</div>
					<div id="contents">
						<p>En vous inscrivant à ce réseau, vous indiquerez votre
							disponibilité professionnelle qui sera visible par une grande
							base de données de recruteurs. Ces chasseurs de tête pourront
							accéder librement à votre profil et vous contacterons pour vos
							qualités. Inscrivez-vous dès maintenant.</p>

						<form id="formInscription" class="form-horizontal"
							action="${pageContext.request.contextPath}/registUser"
							method="post">
							<fieldset>
								<div class="alert alert-info">

									<h4>Information !</h4>
									Pour vous inscrire, merci de remplir le formulaire ci-dessous
								</div>
								<div class="control-group">
									<label class="control-label" for="inputNom">Nom</label>
									<div class="controls">
										<input type="text" name="nom" id="inputNom" placeholder="Doe"
											value=<%=nom%>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPrenom">Prénom</label>
									<div class="controls">
										<input type="text" name="prenom" id="inputPrenom"
											placeholder="John" value=<%=prenom%>>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputEmail">Adresse
										e-mail</label>
									<div class="controls">
										<input type="email" name="email" id="inputEmail"
											placeholder="johndoe@yahoo.fr" value=<%=email%>>
										<!-- Message d'erreur -->
										<c:if test="${request.getAttribute('errorMail')!=''}">
											<span class="text-error"><%=errorMail%></span>
										</c:if>
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
										<c:if test="${request.getAttribute('errorMdpInvalide')!=''}">
											<span class="text-error"><%=errorMdpInvalide%></span>
										</c:if>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputConfirmPassword">Confirmez
										le mot de passe</label>
									<div class="controls">
										<input type="password" name="confirmPassword"
											id="inputConfirmPassword" placeholder="Password">
									</div>
									<!-- Message d'erreur -->
									<c:if test="${request.getAttribute('errorMdp')!=''}">
										<span class="text-error"><%=errorMdp%></span>
									</c:if>
								</div>

								<div class="control-group">
									<label class="control-label" for="inputVille">Ville</label>
									<div class="controls">
										<input type="text" name="ville" id="inputVille"
											placeholder="Orsay" value=<%=ville%>>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn btn-primary">Terminer
											l'inscription</button>
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
	</script>
</body>
</html>
