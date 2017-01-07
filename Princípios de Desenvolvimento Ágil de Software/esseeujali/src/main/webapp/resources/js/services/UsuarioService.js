angular.module('esseeujali')

.factory('Usuario', function($resource){
	return $resource('http://localhost:8080/esseeujali/api/usuario')
});