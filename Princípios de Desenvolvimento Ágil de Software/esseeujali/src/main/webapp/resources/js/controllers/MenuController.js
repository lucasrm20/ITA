angular.module('esseeujali')

.controller('MenuController', function($scope, Auth, $location, Usuario){
	
	$scope.isAutenticado = function(){
		return Auth.getToken() ? true : false;
	};
	
	$scope.logout = function(){
		Auth.logout(function(){
			$location.path("/");
		});
	};
	
	$scope.isPagina = function(nomeDaPagina){
		return $location.path().endsWith(nomeDaPagina);
	};

});