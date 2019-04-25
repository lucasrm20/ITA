angular.module('esseeujali', ['ngRoute', 'ngResource', 'ngStorage'])

.config(function($routeProvider, $httpProvider){
	
	$httpProvider.interceptors.push('authInterceptor');
	
	$routeProvider
		.when('/login', {
			templateUrl: 'resources/templates/login.html',
			controller: 'LoginController'
		})
		
		.when('/livros', {
			templateUrl: 'resources/templates/livros.html',
			controller: 'LivrosController'
		})
		
		.when('/livros/:id', {
			templateUrl: 'resources/templates/livro.html',
			controller: 'LivroController'
		})
		
		.when('/pontuacao', {
			templateUrl: 'resources/templates/pontosTrofeus.html',
			controller: 'PontuacaoTrofeuController'
		})
		
		.when('/ranking', {
			templateUrl: 'resources/templates/ranking.html',
			controller: 'RankingController'
		});
	
	$routeProvider.otherwise({redirectTo: '/login'});
	
});