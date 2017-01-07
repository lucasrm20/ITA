angular.module('esseeujali')

.factory('Livro', function($resource){
	return $resource('http://localhost:8080/esseeujali/api/livros/:id');
});