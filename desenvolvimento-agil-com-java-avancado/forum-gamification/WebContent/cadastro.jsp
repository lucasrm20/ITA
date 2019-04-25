<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Cadastrar</title>
	
	<!-- Bootstrap CSS -->
	<c:url value="/resources" var="resourcesPath" />
	<link href="${resourcesPath }/css/bootstrap.min.css" rel="stylesheet">

	<!-- Biblioteca de Icones Font Awesome -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>

	<div class="container">
		<div class="col-md-6 col-md-offset-3">
		
			<div class="page-header">
				<h1>Cadastrar</h1>
			</div>
			
			<form action="CadastroController" method="post" class="form-horizontal">
			
				<div class="form-group">
                    <label for="login" class="col-sm-2 control-label">Login</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="login" name="login" placeholder="Login" required>
                    </div>
                </div>
				
				<div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="nome" class="col-sm-2 control-label">Nome</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="senha" class="col-sm-2 control-label">Senha</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha" required>
                    </div>
                </div>
                
                <div class="form-group">                	
				   	<div class="col-sm-offset-2 col-sm-10">
				     	<button type="submit" class="btn btn-primary">
				     		<i class="fa fa-sign-in fa-lg" aria-hidden="true"></i> Cadastrar
				     	</button>
				   	</div>
				</div>
			</form>
			
		</div>
	</div>

	<!-- JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Bootstrap Javascript -->
    <script src="${resourcesPath }/js/bootstrap.min.js"></script>
</body>
</html>