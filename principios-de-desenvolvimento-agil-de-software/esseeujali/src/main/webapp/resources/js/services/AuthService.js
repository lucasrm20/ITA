angular.module('esseeujali')

.factory('Auth', function($http, $localStorage){
	
	var urlBase = 'http://localhost:8080/esseeujali/api/auth/';
	
	return {
		getToken: function(){
			return $localStorage.token;
	    },
	    setToken: function(token){
	    	$localStorage.token = token;
	    },
		login: function(data, success, error, end){
			$http.post(urlBase + 'login', data).success(success).error(error).finally(end);
		},
		logout: function(success){
			delete $localStorage.token;
			success();
		}
	};
});