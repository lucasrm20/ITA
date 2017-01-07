angular.module('esseeujali')

.factory('MarcarLeitura', function($resource){
	return $resource('http://localhost:8080/esseeujali/api/livros/:id/marcarLeitura');
});