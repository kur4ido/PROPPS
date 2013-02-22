<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>PROPPS - Profil membre</title>

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

<link rel="icon" href="${pageContext.request.contextPath}/img/propps.ico">
</head>

<body>
  <!-- Navbar
    ================================================== -->

<% String ville_Membre_Select = (String) request.getAttribute("ville_Membre_Select"); %>
<% String ID_Membre_Courant = (String) request.getAttribute("ID_Membre_Courant"); %>


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
					<form action="${pageContext.request.contextPath}/quickSearch"
						class="navbar-search pull-left" method="post">
						<input type="text" name="quicksearch" placeholder="Recherches"
							class="search-query span2">
							<input type="hidden" name="ID_Membre_Courant" value=${membreCourant.ID_Utilisateur } >
					</form>	

					<ul class="nav pull-right">
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-list-alt"></i>
								Invitations <c:if test="${requestScope.nbNotif>'0'}" ><span class="badge badge-info">${requestScope.nbNotif}</span></c:if> </a>
							<ul class="dropdown-menu">
								<c:if test="${requestScope.nbNotif>0}">
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${requestScope.mapNotifRecept}" var="entry">
									<c:set var="count" value="${count + 1}" scope="page"/>
									<li>
										<div id="notification">
											<div id="notif_contact">
												<form action="${pageContext.request.contextPath}/relationAnswer" method="post">
													${entry.key}</br>
													<input type="hidden" name="ID_Membre_Courant" value="${membreCourant.ID_Utilisateur}" >
													<input type="hidden" name="ID_Notification" value="${entry.value}" >
													<button class="btn btn-mini btn-success" name="estAccepte"  value="true" type="submit">Accepter</button>
													<button class="btn btn-mini btn-danger" name="estAccepte"  value="false" type="submit">Refuser</button>
												</form>
											</div>
										</div>
									</li>
									<c:if test="${count <  requestScope.nbNotif}">
										<li class="divider"></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${requestScope.nbNotif==0}">
								<li><p>Pas de nouvelles notifications.</p></li>
							</c:if>
							</ul></li>
						<li class="divider-vertical"></li>
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-home"></i>
								${membreCourant.sPrenom } ${membreCourant.sNom } <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/seeCurrentUserProfile?ID_Membre_Courant=${membreCourant.ID_Utilisateur}" ><i class="icon-user"></i> Mon
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
	<div id="header-compte">

		<div class="container">
			<div class="row">
				<div class="span12">
					<div id="fiche_user">
						<img src="${pageContext.request.contextPath}/img/oneil.jpeg" height="140" class="img-polaroid">
						<div id="detail_user">
							<h2>${requestScope.membreSelect.sPrenom} ${requestScope.membreSelect.sNom}</h2>
							<br>
							<p class="lead">
								<i class="icon-briefcase"></i> Poste (profil), Entreprise<br>
								<i class="icon-calendar"></i> Disponibilité <br> <i
									class="icon-home"></i> <%=ville_Membre_Select %>

							</p>
							<c:if test="${requestScope.isContact=='nonAmis'}" >
								<form action="${pageContext.request.contextPath}/relationAsk" method="post">
									<input type="hidden" name="ID_Membre_Courant" value=${membreCourant.ID_Utilisateur } >
									<input type="hidden" name="ID_Membre_Select" value=${membreSelect.ID_Utilisateur } >
										<a href="#" class="btn btn-primary btn-small" type="button" 
										onclick="javascript:document.forms[1].submit();return false;" ><i
										class="icon-plus-sign icon-white"></i> Ajouter à  mes contacts</a>
								</form>
							</c:if>
							<c:if test="${requestScope.isContact=='amis'}">
								<a href="#" class="btn btn-primary btn-small" type="button" 
								onclick="" disabled="true" > Cette personne fait deja parti de vos contacts </a>
							</c:if>
							<c:if test="${requestScope.isContact=='attenteConfirm'}">
								<a href="#" class="btn btn-primary btn-small" type="button" 
								onclick="" disabled="true" > En attente de confirmation </a>
							</c:if>
							<c:if test="${requestScope.isContact=='OuiNon'}">
								<button class="btn btn-mini btn-success" type="button">Accepter</button>
								<button class="btn btn-mini btn-danger" type="button">Refuser</button>
							</c:if>
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
						
						<c:forEach items="${requestScope.lstExpsPro}" var="expPro" >
							<div class="row">
								<div class="span2">
										<p>
											<!-- Bouton pour déclencher l'ouverture du div -->
											<a data-toggle="collapse" data-target="#modifExperience${countExp}">
												<i class="icon-pencil"></i>
											</a>
										<h4>${expPro.sPosteOccupe} (${expPro.profil.sNom})</h4>
										${expPro.societe.sNom}
										</p>
										<p class="muted">${expPro.dtDebut} - <c:if test="${not empty expPro.dtFin}"> ${expPro.dtFin}</c:if><c:if test="${empty expPro.dtFin}"> Aujourd'hui</c:if></p>
		<!-- 								<p class="muted">Prestation</p> -->
										<p>
											<c:forEach items="${expPro.lstExpertise}" var="expert">
												<span class="label label-info">
													${expert.sDomaine } - ${expert.sType }
												</span>
											</c:forEach>
										</p>
									</div>
									<div class="span7">
										<p>${expPro.sDescription}</p>
		
									</div>
	
							</div>
						</c:forEach>

						<hr class="bs-docs-separator">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/expertise.png">
							<h3>Domaines d'expertise</h3>
						</div>
						<div class="expertise">
							<span class="label label-info">Emission - Back Office</span> <span
								class="label label-info">Commerçant - Front Office</span> <span
								class="label label-info">Automates - Front Office</span>
						</div>
					</div>
				</div>
				<div class="span3">

					<div id="last-add">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/contact.png">
							<h3>Liste des contacts</h3>
						</div>
						<c:forEach var="member" items="${requestScope.LstContacts}" >
							<div id="contact">
								<img src="${pageContext.request.contextPath}/img/people.png" width="64">
								<div id="description_contact">
									<a href="${pageContext.request.contextPath}/seeUserProfile?ID_Membre_Courant=${membreCourant.ID_Utilisateur }&ID_Membre_Select=${member.ID_Utilisateur }" >${member.sPrenom } ${member.sNom }</a><br> Profession, Entreprise
								</div>
							</div>
						</c:forEach>
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
