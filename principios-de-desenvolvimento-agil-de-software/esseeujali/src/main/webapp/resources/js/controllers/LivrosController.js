angular.module('esseeujali')

.controller('LivrosController', function($scope, Livro){
	
	Livro.get().$promise
		.then(function(data){
			$scope.livros = data.livros.livros;
		})
		.catch(function(err){
			var msg = {
				txt: err.status + ' - ' + err.statusText
			};
			
			console.warn(msg.txt);
			console.log(err);
		});
	
});