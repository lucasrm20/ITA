angular.module('esseeujali')

.directive('menuPrincipal', function(){
	return {
		restrict: 'E',
		templateUrl: 'resources/templates/menu.html'
	};
})

.directive('rodape', function(){
	return {
		restrict: 'E',
		templateUrl: 'resources/templates/footer.html'
	};
});