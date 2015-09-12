var contextPath = "/tvshows";

TvShowsApp.factory('recomendService', function($resource) {

	return $resource(contextPath + '/data/rest/shows/recomend', {}, {
		recomend : {
			method : 'POST',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});

TvShowsApp.factory('watchlistService', function($resource) {

	return $resource(contextPath + '/data/rest/shows/addToWatchlist', {}, {
		addToWatchlist : {
			method : 'POST',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});

TvShowsApp.factory('getWatchlistService', function($resource) {

	return $resource(contextPath + '/data/rest/shows/getWatchlist');
});

TvShowsApp.factory('watchlistService', function($resource) {

	return $resource(contextPath + '/data/rest/shows/addToWatchlist', {}, {
		addToWatchlist : {
			method : 'POST',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});

TvShowsApp.factory('removeFromWatchlistService', function($resource) {

	return $resource(contextPath + '/data/rest/shows/removeFromWatchlist', {}, {
		remove : {
			method : 'POST',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});

TvShowsApp.service('checkRecommendation', function($resource) {

	    this.check = function(id){
           return $resource(contextPath + '/data/rest/shows/recomend/check', 
                { callback: 'JSON_CALLBACK' }, 
                      { get: { method: "JSONP" }}
        )};
});

TvShowsApp.factory('unRecommendService', function($resource) {

	   	return $resource(contextPath + '/data/rest/shows/unrecomend', {}, {
		unRecommend : {                                  
			method : 'POST',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}
	});
});

