<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Exibe Tópico</title>
	
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
		      <a class="navbar-brand">Fórum Gamification</a>
		    </div>
		
		    <div class="collapse navbar-collapse" id="links">
		      <ul class="nav navbar-nav navbar-right">
		        <li>
		        	<c:url value='/TopicosController' var="linkTopicos" />
		        	<a href="${linkTopicos }">Tópicos</a>
		        </li>
		        <li>
		        	<a>Olá, ${usuario.nome }</a>
		        </li>
		      </ul>
		    </div>
		    
		  </div>
		</nav>
		
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="page-header">
					<h2>
						${topico.titulo }
						<small>${topico.usuario.nome }</small>
					</h2>
				</div>
				<p>${topico.conteudo }</p>
				
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="page-header">
					<h3>Comentários</h3>
				</div>
				
				<c:forEach items="${comentarios }" var="comentario">
					<div class="media">
						<div class="media-body">
							<h4 class="media-heading">${comentario.usuario.nome }</h4>
							<p>${comentario.comentario }</p>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="page-header">
					<h3>Adicionar Comentário</h3>
				</div>
				
				<form class="form-horizontal" action="ComentarioController" method="post">
					<input type="hidden" name="topicoId" value="${topico.id_topico }">
				
					<div class="form-group">
	                   <label for="comentario" class="col-sm-2 control-label">Comentário</label>
	                   <div class="col-sm-10">
	                       <textarea class="form-control" id="comentario" name="comentario" placeholder="Novo Comentário" required></textarea>
	                   </div>
	               </div>
	               
	               <div class="form-group">                	
					   	<div class="col-sm-offset-2 col-sm-10">
					     	<button type="submit" class="btn btn-primary">
					     		<i class="fa fa-paper-plane-o fa-lg" aria-hidden="true"></i> Enviar
					     	</button>
					   	</div>
					</div>
				</form>
				
			</div>
		</div>
	
	</div>

	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Bootstrap Javascript -->
    <script src="${resourcesPath }/js/bootstrap.min.js"></script>
</body>
</html>