<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<title>Tópicos</title>
	
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
		        <li class="active">
		        	<c:url value='/TopicosController' var="linkTopicos" />
		        	<a href="${linkTopicos }">Tópicos</a>
		        </li>
		        <li>
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
			<div class="col-xs-12">
				<div class="page-header">
					<h1>Tópicos</h1>
				</div>
	
				<div class="text-right" style="padding-bottom: 20px;">
					<c:url value='/InserirTopicoController' var="linkInserirTopico" />
					<a href="${linkInserirTopico }" class="btn btn-primary">
						<i class="fa fa-plus" aria-hidden="true"></i> Inserir Tópico
					</a>
				</div>
				
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th class="active">Tópico</th>
							<th class="active">Autor</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${topicos }" var="topico">
							<tr>
								<td class="col-md-10 col-xs-8">
									<c:url value="/TopicoController" var="topicoLink">
										<c:param name="topicoId" value="${topico.id_topico }" />
									</c:url>
									<a href="${topicoLink }">${topico.titulo }</a>
								</td>
								<td>${topico.usuario.nome }</td>
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