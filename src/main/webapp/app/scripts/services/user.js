var contextPath = "/tvshows";

TvShowsApp.factory('UserService', function($resource) {

	return $resource(contextPath + '/data/rest/user/:action', {}, {
		authenticate : {
			method : 'POST',
			params : {
				'action' : 'authenticate'
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		},
        
        
        register : {
			method : 'POST',
			params : {
				'action' : 'register'
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
        
	});
});