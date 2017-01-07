angular.module('esseeujali')

.controller('RankingController', function($scope, Ranking){
	
	Ranking.get().$promise
		.then(function(data){
			$scope.ranking = data.ranking.ranking;
		})
		.catch(function(err){
			console.log(err);
		});
});