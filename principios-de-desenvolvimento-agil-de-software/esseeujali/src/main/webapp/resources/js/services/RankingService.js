angular.module('esseeujali')

.factory('Ranking', function($resource){
	return $resource('http://localhost:8080/esseeujali/api/ranking');
});