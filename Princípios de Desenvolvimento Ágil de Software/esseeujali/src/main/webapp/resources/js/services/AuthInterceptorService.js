angular.module('esseeujali')

.factory('authInterceptor', function($q, $location, $localStorage){
	
	var authInterceptor = {
		request: function(config){
			config.headers = config.headers || {};
            
			if ($localStorage.token) {
                config.headers.Authorization = $localStorage.token;
            }
			
			return config;
		},
		responseError: function(resposta){
			if(resposta.status == 401){
				if($localStorage.token){
					delete $localStorage.token;
				}
				$location.path('/login');
			}

			return $q.reject(resposta);
		}
	};

	return authInterceptor;
});