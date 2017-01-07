angular.module('esseeujali')

.controller('PontuacaoTrofeuController', function($scope, Usuario){
	
	function encapsularEmUmArray(dados){
		if(!Array.isArray(dados)){
			dados = [dados];
		}
		
		return dados;
	};
	
	function corrigeDados(){
		if($scope.usuario.livrosLidos)
			$scope.usuario.livrosLidos = encapsularEmUmArray($scope.usuario.livrosLidos);
		if($scope.usuario.trofeus)
			$scope.usuario.trofeus = encapsularEmUmArray($scope.usuario.trofeus);
	};
	
	Usuario.get().$promise
		.then(function(data){
			$scope.usuario = data.usuario;
			console.log($scope.usuario);
			
			corrigeDados();
		})
		.catch(function(err){
			console.log(err);
		});
	
});