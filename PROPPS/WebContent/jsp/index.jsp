<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>PROPPS - Connexion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="./css/bootstrap.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="./ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="./ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="./ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="./ico/apple-touch-icon-57-precomposed.png">
<link rel="icon" href="img/propps.ico">
</head>

<body data-spy="scroll">
	<!-- Navbar
    ================================================== -->
	<%
		String email = (String) request.getAttribute("email");
	%>
	<%
		String error = (String) request.getAttribute("error");
	%>


	<div id="header">
		<div class="container">
			<div class="row">
				<div class="span12">
					<h1>ProPPS</h1>
				</div>
			</div>
		</div>
	</div>

	<div id="wrap">
		<div class="container">
			<div class="row">
				<div class="span8">
					<div class="page-header">
						<h1>R�seau Professionnel Polytech Paris-Sud</h1>
					</div>
					<p class="lead">Bienvenue sur le r�seau Professionnel de
						Polytech Paris-Sud.</p>
					<p class="lead">Il s'agit du r�seau social mis � votre
						disposition pour booster votre carri�re ou constituer un
						recrutement de qualit�. En tant que membre, il permet de recenser
						les contacts professionnels qui vous permettront d'obtenir un
						emploi, et en tant que recruteur ce r�seau vous met � disposition
						des candidats comp�tents et correspondant � vos besoins.</p>
				</div>
				<div class="span4">
					<form class="form-signin" action="connect" method="post">
						<h2 class="form-signin-heading">Connectez-vous</h2>
						<input type="text" class="input-block-level" name="email"
							placeholder="Adresse email" value=<%=email%>> <input
							type="password" class="input-block-level" name="password"
							placeholder="Mot de passe">
						<c:if test="${request.getAttribute('error')!=''}">
							<span class="text-error"><%=error%></span>
						</c:if>
						<label class="checkbox"> <input type="checkbox"
							name="remember" value="1"> Rester connect� <!--- <a href="#">Mot de passe oubli�?</a>-->
						</label>
						<button class="btn btn-large btn-primary" type="submit">Connexion</button>
					</form>
					<div class="page-header">
						<h2>Inscription</h2>
					</div>
					<a href="pages/inscription1.html"
						class="btn btn-large  btn-block btn-primary">En tant que
						Membre</a> <a href="pages/inscription_ent.html"
						class="btn btn-large btn-block">En tant que Recruteur</a>


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

	<script src="js/jquery-1.9.0.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
