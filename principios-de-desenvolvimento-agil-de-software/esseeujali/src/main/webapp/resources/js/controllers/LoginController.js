angular.module('esseeujali')

.controller('LoginController', function($scope, Auth, $location){
	
	if(Auth.getToken()){
		$location.path("/livros");
	}
	
	$scope.login = {};
	$scope.isSubmitting = false;
	
	$scope.logar = function(){
		var login = {
				login: $scope.login
		};
		
		$scope.isSubmitting = true;
		
		Auth.login(
				login,
				function(data){
					Auth.setToken(data.token.token);
					$location.path("/livros");
				},
				function(err){
					console.log(err);
					$scope.message = {
							message: 'Login ou Senha Inválido'
					}
				},
				function(){					
					$scope.login = {};
					$scope.isSubmitting = false;
				}
		);
		
	};
	
});