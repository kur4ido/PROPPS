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
							<input type="hidden" name="ID_Membre_Courant" value=<%=ID_Membre_Courant %> >
					</form>
					<ul class="nav">
						<li><a href="pages/recherche_membre.html"><i
								class="icon-search"></i> Recherche Avancée</a></li>
					</ul>
					<ul class="nav pull-right">
						<li class="dropdown"><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"><i class="icon-list-alt"></i>
								Invitations 
								<c:if test="${requestScope.nbNotif>'0'}" ><span class="badge badge-info">${requestScope.nbNotif}</span></c:if> </a>
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
													<input type="hidden" name="ID_Membre_Courant" value=<%=ID_Membre_Courant %> >
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
								<%=prenom %> <%=nom %> <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/seeCurrentUserProfile?ID_Membre_Courant=<%=ID_Membre_Courant %>" ></i> Mon
										compte</a></li>
								<li><a href="#"><i class="icon-inbox"></i> Inbox</a></li>
								<li><a href="${pageContext.request.contextPath}/modifPersonalInfo?ID_Membre_Courant=<%=ID_Membre_Courant %>"><i class="icon-wrench"></i>
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
							<a href="${pageContext.request.contextPath}/modifPersonalInfo?ID_Membre_Courant=<%=ID_Membre_Courant %>" class="btn btn-primary btn-small"
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
							<!-- Bouton pour déclencher l'ouverture du div -->
							<a class="pull-right" data-toggle="collapse"
								data-target="#experience"> <i class="icon-pencil"></i>
							</a>
						</div>
						<!-- Div qui permet de créer une nouvelle expérience -->
						<div id="experience" class="collapse in">
							<form id="formAddExp" class="" action="${pageContext.request.contextPath}/addExpPro" method="post" >
								<fieldset>
									<legend>Ajouter une nouvelle expérience
										professionnelle</legend>
									<div class="row">
										<div class="span4">
											<div class="control-group">
												<label class="control-label" for="inputSociété"><b>Société</b></label>
												<div class="controls">
													<input type="text" name="societe" id="inputSociété"
														value="Dassault">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="expertise"> <b>Domaines
														d'expertise</b></label>
												<div class="controls">
													<label class="checkbox"> <input type="checkbox"
														name="expertise" value="EmissionFront"> Emission
														- Front office
													</label> <label class="checkbox"> <input type="checkbox"
														name="expertise" value="EmissionBack" checked>
														Emission - Back office
													</label> <label class="checkbox"> <input type="checkbox"
														name="expertise" value="CommercantFront">
														Commerçant - Front office
													</label> <label class="checkbox"> <input type="checkbox"
														name="expertise" value="CommercantBack">
														Commerçant - Back office
													</label> <label class="checkbox"> <input type="checkbox"
														name="expertise" value="AutomatesFront">
														Automates - Front office
													</label> <label class="checkbox"> <input type="checkbox"
														name="expertise" value="AutomatesBack"> Automates
														- Back office
													</label>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="profil"><b>Profil</b>
												</label>
												<div class="controls">
													<label class="radio inline"> <input type="radio"
														name="profil" id="optionsRadios1" value="MOA" checked>
														MOA
													</label> <label class="radio inline"> <input type="radio"
														name="profil" id="optionsRadios2" value="MOE"> MOE
													</label> <label class="radio"> <input type="radio"
														name="profil" id="optionsRadios3" value="DCompetence">
														Double Compétence
													</label>
												</div>
											</div>

											<div class="control-group">
												<label class="control-label" for="dispo"><b>Disponibilité</b>
												</label>
												<div class="controls">
													<label class="radio inline"> <input type="radio"
														name="dispo" id="optionsRadios1" value="Prestation">
														Prestation
													</label> <label class="radio inline"> <input type="radio"
														name="dispo" id="optionsRadios2" value="Contrat" checked>
														Contrat
													</label>
												</div>
											</div>
										</div>

										<div class="span4">
											<div class="control-group">
												<label class="control-label" for="dateDebut"><b>De</b></label>
												<div class="controls">
													<div class="input-append date" id="dp3"
														data-date="04/02/2013" data-date-format="dd/mm/yyyy"
														class="span1">
														<input class="span2" name="dateDebut" size="16" type="text"
															value="04/02/2013"> <span class="add-on"><i
															class="icon-calendar"></i></span>
													</div>

												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="dateFin"><b>à </b></label>
												<div class="controls">
													<div class="input-append date" id="dp3"
														data-date="04/02/2013" data-date-format="dd/mm/yyyy"
														class="span1">
														<input class="span2" size="16" name="dateFin" type="text" value="">
														<span class="add-on"><i class="icon-calendar"></i></span>
													</div>
													<a href="#" rel="tooltip" data-placement="right"
														title="Ne renseigner une date de fin que si l'expérience professionnelle est terminée"><i
														class="icon-info-sign"></i></a>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="inputDirection"><b>Direction
														au sein de la société</b></label>
												<div class="controls">
													<input type="text" name="direction" id="inputDirection"
														placeholder="...">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="inputPoste"><b>Poste
														occupé</b></label>
												<div class="controls">
													<input type="text" name="poste" id="inputPoste"
														placeholder="Ingénieur d'étude">
												</div>

											</div>

											<div class="control-group">
												<label class="control-label" for="inputDescription"><b>Description</b></label>
												<div class="controls">
													<textarea row="3" name="description" id="inputDescription"
														placeholder="Description"></textarea>
												</div>
											</div>
											<div class="control-group">
												<div class="controls">
													<button type="submit" class="btn btn-primary">Ajouter</button>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</form>
						</div>

						<div class="row">
							<div class="span2">
								<p>
									<!-- Bouton pour déclencher l'ouverture du div -->
									<a data-toggle="collapse" data-target="#modifExperience1">
										<i class="icon-pencil"></i>
									</a>
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
							<div class="span9">
								<!-- Le div permet de modifier l'expérience pro sélectionné, il faut rajouter un numéro pour chaque div pour le différencier des autres -->
								<div id="modifExperience1" class="modifExperience collapse in">
									<form id="formAddExp" class="" action="">
										<fieldset>
											<div class="row">
												<div class="span4">
													<div class="control-group">
														<label class="control-label" for="inputSociété"><b>Société</b></label>
														<div class="controls">
															<input type="text" name="societe" id="inputSociété"
																value="Dassault">
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="expertise"> <b>Domaines
																d'expertise</b></label>
														<div class="controls">
															<label class="checkbox"> <input type="checkbox"
																name="expertise1" value="EmissionFront">
																Emission - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise2" value="EmissionBack" checked>
																Emission - Back office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise3" value="CommercantFront">
																Commerçant - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise4" value="CommercantBack">
																Commerçant - Back office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise5" value="AutomatesFront">
																Automates - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise6" value="AutomatesBack">
																Automates - Back office
															</label>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="profil"><b>Profil</b>
														</label>
														<div class="controls">
															<label class="radio inline"> <input type="radio"
																name="profil" id="optionsRadios1" value="MOA" checked>
																MOA
															</label> <label class="radio inline"> <input type="radio"
																name="profil" id="optionsRadios2" value="MOE">
																MOE
															</label> <label class="radio"> <input type="radio"
																name="profil" id="optionsRadios3" value="DCompetence">
																Double Compétence
															</label>
														</div>
													</div>

													<div class="control-group">
														<label class="control-label" for="dispo"><b>Disponibilité</b>
														</label>
														<div class="controls">
															<label class="radio inline"> <input type="radio"
																name="dispo" id="optionsRadios1" value="Prestation">
																Prestation
															</label> <label class="radio inline"> <input type="radio"
																name="dispo" id="optionsRadios2" value="Contrat" checked>
																Contrat
															</label>
														</div>
													</div>
												</div>

												<div class="span4">
													<div class="control-group">
														<label class="control-label" for="dateDebut"><b>De</b></label>
														<div class="controls">
															<div class="input-append date" id="dp3"
																data-date="04/02/2013" data-date-format="dd/mm/yyyy"
																class="span1">
																<input class="span2" size="16" type="text"
																	value="04/02/2013"> <span class="add-on"><i
																	class="icon-calendar"></i></span>
															</div>

														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="dateFin"><b>à </b></label>
														<div class="controls">
															<div class="input-append date" id="dp3"
																data-date="04/02/2013" data-date-format="dd/mm/yyyy"
																class="span1">
																<input class="span2" size="16" type="text" value="">
																<span class="add-on"><i class="icon-calendar"></i></span>
															</div>
															<a href="#" rel="tooltip" data-placement="right"
																title="Ne renseigner une date de fin que si l'expérience professionnelle est terminée"><i
																class="icon-info-sign"></i></a>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="inputDirection"><b>Direction
																au sein de la société</b></label>
														<div class="controls">
															<input type="text" name="direction" id="inputDirection"
																placeholder="...">
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="inputPoste"><b>Poste
																occupé</b></label>
														<div class="controls">
															<input type="email" name="poste" id="inputPoste"
																placeholder="Ingénieur d'étude">
														</div>

													</div>

													<div class="control-group">
														<label class="control-label" for="inputDescription"><b>Description</b></label>
														<div class="controls">
															<textarea row="3" name="description"
																id="inputDescription" placeholder="Description"></textarea>
														</div>
													</div>
													<div class="control-group">
														<div class="controls">
															<button type="submit" class="btn btn-primary">Modifier</button>
															<a href="" type="button" class="btn">Supprimer</a>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							</div>

						</div>

						<div class="row">
							<div class="span2">
								<p>
									<!-- Bouton pour déclencher l'ouverture du div -->
									<a data-toggle="collapse" data-target="#modifExperience2">
										<i class="icon-pencil"></i>
									</a>
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
							<div class="span9">
								<!-- Le div permet de modifier l'expérience pro sélectionné, il faut rajouter un numéro pour chaque div pour le différencier des autres -->
								<div id="modifExperience2" class="modifExperience collapse in">
									<form id="formAddExp" class="" action="">
										<fieldset>
											<div class="row">
												<div class="span4">
													<div class="control-group">
														<label class="control-label" for="inputSociété"><b>Société</b></label>
														<div class="controls">
															<input type="text" name="societe" id="inputSociété"
																value="Dassault">
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="expertise"> <b>Domaines
																d'expertise</b></label>
														<div class="controls">
															<label class="checkbox"> <input type="checkbox"
																name="expertise1" value="EmissionFront">
																Emission - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise2" value="EmissionBack" checked>
																Emission - Back office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise3" value="CommercantFront">
																Commerçant - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise4" value="CommercantBack">
																Commerçant - Back office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise5" value="AutomatesFront">
																Automates - Front office
															</label> <label class="checkbox"> <input type="checkbox"
																name="expertise6" value="AutomatesBack">
																Automates - Back office
															</label>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="profil"><b>Profil</b>
														</label>
														<div class="controls">
															<label class="radio inline"> <input type="radio"
																name="profil" id="optionsRadios1" value="MOA" checked>
																MOA
															</label> <label class="radio inline"> <input type="radio"
																name="profil" id="optionsRadios2" value="MOE">
																MOE
															</label> <label class="radio"> <input type="radio"
																name="profil" id="optionsRadios3" value="DCompetence">
																Double Compétence
															</label>
														</div>
													</div>

													<div class="control-group">
														<label class="control-label" for="dispo"><b>Disponibilité</b>
														</label>
														<div class="controls">
															<label class="radio inline"> <input type="radio"
																name="dispo" id="optionsRadios1" value="Prestation">
																Prestation
															</label> <label class="radio inline"> <input type="radio"
																name="dispo" id="optionsRadios2" value="Contrat" checked>
																Contrat
															</label>
														</div>
													</div>
												</div>

												<div class="span4">
													<div class="control-group">
														<label class="control-label" for="dateDebut"><b>De</b></label>
														<div class="controls">
															<div class="input-append date" id="dp3"
																data-date="04/02/2013" data-date-format="dd/mm/yyyy"
																class="span1">
																<input class="span2" size="16" type="text"
																	value="04/02/2013"> <span class="add-on"><i
																	class="icon-calendar"></i></span>
															</div>

														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="dateFin"><b>à </b></label>
														<div class="controls">
															<div class="input-append date" id="dp3"
																data-date="04/02/2013" data-date-format="dd/mm/yyyy"
																class="span1">
																<input class="span2" size="16" type="text" value="">
																<span class="add-on"><i class="icon-calendar"></i></span>
															</div>
															<a href="#" rel="tooltip" data-placement="right"
																title="Ne renseigner une date de fin que si l'expérience professionnelle est terminée"><i
																class="icon-info-sign"></i></a>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="inputDirection"><b>Direction
																au sein de la société</b></label>
														<div class="controls">
															<input type="text" name="direction" id="inputDirection"
																placeholder="...">
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="inputPoste"><b>Poste
																occupé</b></label>
														<div class="controls">
															<input type="email" name="poste" id="inputPoste"
																placeholder="Ingénieur d'étude">
														</div>

													</div>

													<div class="control-group">
														<label class="control-label" for="inputDescription"><b>Description</b></label>
														<div class="controls">
															<textarea row="3" name="description"
																id="inputDescription" placeholder="Description"></textarea>
														</div>
													</div>
													<div class="control-group">
														<div class="controls">
															<button type="submit" class="btn btn-primary">Ajouter</button>
															<a href="" type="button" class="btn">Supprimer</a>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
							</div>

						</div>
						<hr class="bs-docs-separator">
						<div id="title">
							<img src="${pageContext.request.contextPath}/img/expertise.png">
							<h3>Domaines d'expertise</h3>
							<!-- Bouton pour déclencher l'ouverture du div -->
							<a class="pull-right" data-toggle="collapse"
								data-target="#expertise"> <i class="icon-pencil"></i>
							</a>

						</div>
						<!-- Div qui permet d'ajouter un domaine d'expertise -->
						<div id="expertise" class="collapse in">
							<form id="formAddDomExp" class="form-horizontal" action="">
								<fieldset>
									<legend>Ajouter un nouveau domaine d'expertise</legend>

									<div class="control-group">
										<label class="control-label" for="domaine">Domaine</label>
										<div class="controls">
											<select name="domaine">
												<option>Emission - Front Office</option>
												<option>Emission - Back Office</option>
												<option>Commerçant - Front Office</option>
												<option>Commerçant - Back Office</option>
												<option>Automates - Front Office</option>
												<option>Automates - Back Office</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Ajouter</button>
										</div>
									</div>
								</fieldset>
								<legend>Modifier vos domaines d'expertise</legend>
								<!-- L'adresse du lien contient l'identifiant du domaine d'expertise à  supprimer -->
								<table>
									<tbody>
										<tr>
											<td>Emission - Back Office</td>
											<td><a href="deleteDomaineId1.html"><i
													class="icon-remove-sign"></i></a></td>
										</tr>
										<tr>
											<td>Commerçant - Front Office</td>
											<td><a href="deleteDomaineId2.html"><i
													class="icon-remove-sign"></i></a></td>
										</tr>
										<tr>
											<td>Automates - Front Office</td>
											<td><a href="deleteDomaineId3.html"><i
													class="icon-remove-sign"></i></a></td>
										</tr>
									</tbody>
								</table>
							</form>




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
							<h3>Derniers ajouts</h3>
						</div>
							<c:forEach var="member" items="${requestScope.LstContacts}" >
								<div id="contact">
									<img src="${pageContext.request.contextPath}/img/people.png" width="64">
									<div id="description_contact">
										<a href="${pageContext.request.contextPath}/seeUserProfile?ID_Membre_Courant=<%=ID_Membre_Courant %>&ID_Membre_Select=${member.ID_Utilisateur }" >${member.sPrenom } ${member.sNom }</a><br> Profession, Entreprise
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



	<!-- JS supplementaire -->
	<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>

	<script>
		$(document).off('touchstart.dropdown.data-api');
		$('.dropdown-toggle').dropdown();

		$('.date').datepicker();
		$('#expertise').collapse({
			toggle : true
		})

		$('#experience').collapse({
			toggle : true
		})

		$('.modifExperience').collapse({
			toggle : true
		})
		$(function() {
			$("[rel='tooltip']").tooltip();
		});
	</script>
</body>
</html>