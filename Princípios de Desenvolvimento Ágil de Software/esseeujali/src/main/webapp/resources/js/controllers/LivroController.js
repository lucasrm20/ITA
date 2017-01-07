angular.module('esseeujali')

.controller('LivroController', function($scope, $routeParams, Livro, MarcarLeitura, Usuario){
	
	$scope.livrosLidos = [];
	
	$scope.processandoLeitura = false;
	$scope.isLivroLido = false;
	
	function extrairLivrosLidos(dados){
		if(dados.livrosLidos){
			if(Array.isArray(dados.livrosLidos)){
				$scope.livrosLidos = dados.livrosLidos;
			} else {				
				$scope.livrosLidos = [dados.livrosLidos];
			}
		}
	};
	
	function verificaSeJaFoiLido(){
		var encontrou = $scope.livrosLidos.find(function(item){
			return item.id === $scope.livro.id;
		});
		
		$scope.isLivroLido = encontrou ? true : false;
	};
	
	function buscaInfoLivro(){
		Livro.get({id: $routeParams.id}).$promise
			.then(function(data){
				$scope.livro = data.livro;
				verificaSeJaFoiLido();
			})
			.catch(function(err){
				var msg = {
					txt: err.status + ' - ' + err.statusText
				};
				
				console.warn(msg.txt);
				console.log(err);
			});
	};
	
	Usuario.get().$promise
		.then(function(data){
			extrairLivrosLidos(data.usuario);
		})
		.catch(function(err){
			console.log(err);
		})
		.finally(function(){
			buscaInfoLivro();
		});
	
	$scope.marcarLeitura = function(){
		$scope.processandoLeitura = true;
		
		MarcarLeitura.save({id: $routeParams.id}, {}).$promise
			.then(function(){
				console.log('Ok');
				$scope.isLivroLido = true;
			})
			.catch(function(err){
				var msg = {
					txt: err.status + ' - ' + err.statusText
				};
				
				console.warn(msg.txt);
				console.log(err);
			})
			.finally(function(){
				$scope.processandoLeitura = false;
			});
	};
	
});