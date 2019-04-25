<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<title>Login</title>
	
	<!-- Bootstrap CSS -->
	<c:url value="/resources" var="resourcesPath" />
	<link href="${resourcesPath }/css/bootstrap.min.css" rel="stylesheet">

	<!-- Biblioteca de Icones Font Awesome -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>

	<div class="container">
		<div class="col-md-4 col-md-offset-4">
		
			<div class="page-header">
				<h1>Login</h1>
			</div>
			
			<c:if test="${erro != null}">
				<div class="alert alert-danger alert-dismissible fade in" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<span class="fa fa-exclamation-triangle" aria-hidden="true"></span>
					<span class="sr-only">Error:</span>
					${erro }
				</div>
			</c:if>
			
			<form action="LoginController" method="post">
				<div class="form-group">
					<label>Login</label>
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon">
							<i class="fa fa-user fa-fw" aria-hidden="true"></i>
						</span>
						<input type="text" name="login" class="form-control" placeholder="Login" required autofocus/>
					</div>
				</div>
			
				<div class="form-group">
					<label>Senha</label>
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon">
							<i class="fa fa-key fa-fw" aria-hidden="true"></i>
						</span>
						<input type="password" name="senha" class="form-control" placeholder="Senha" required />
					</div>
				</div>
				
				<div class="text-right">
					<button type="submit" class="btn btn-primary btn-block">
						<i class="fa fa-sign-in fa-lg" aria-hidden="true"></i> Login
					</button>
				</div>
			</form>
			
			<a href="<c:url value='/CadastroController' />" class="pull-left">Cadastrar-se</a>
			
		</div>
    </div>
	
	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Bootstrap Javascript -->
    <script src="${resourcesPath }/js/bootstrap.min.js"></script>
</body>
</html>