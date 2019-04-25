<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<title>Ranking</title>

	<!-- Bootstrap CSS -->
	<c:url value="/resources" var="resourcesPath" />
	<link href="${resourcesPath }/css/bootstrap.min.css" rel="stylesheet">

	<!-- Biblioteca de Icones Font Awesome -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>
	
	<div class="container">
		
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		  
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#links" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand">Forum Gamification</a>
		    </div>
		
		    <div class="collapse navbar-collapse" id="links">
		      <ul class="nav navbar-nav navbar-right">
		        <li>
		        	<c:url value='/TopicosController' var="linkTopicos" />
		        	<a href="${linkTopicos }">Tópicos</a>
		        </li>
		        <li class="active">
		        	<c:url value='/RankingController' var="linkRanking" />
		        	<a href="${linkRanking }">Ranking</a>
		        </li>
		        <li>
		        	<a>Olá, ${usuario.nome }</a>
		        </li>
		      </ul>
		    </div>
		    
		  </div>
		</nav>
		
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>Ranking</h1>
				</div>
				
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th class="active">Colocação</th>
							<th class="active">Nome</th>
							<th class="active">Login</th>
							<th class="active">Pontos</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ranking }" var="usuario" varStatus="i">
							<tr>
								<td class="col-xs-1">${i.index + 1 }</td>
								<td>${usuario.nome }</td>
								<td>${usuario.login }</td>
								<td>${usuario.pontos }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		</div>
	</div>

	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Bootstrap Javascript -->
    <script src="${resourcesPath }/js/bootstrap.min.js"></script>
</body>
</html>