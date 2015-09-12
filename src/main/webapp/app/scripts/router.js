/**
 * 
 */

TvShowsApp.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
	
	$routeProvider.when('/', {
		templateUrl: 'views/main.html',
		controller: 'MainCtrl'
	});
	
	$routeProvider.when('/movies', {
		templateUrl: 'views/movies.html',
		controller: 'MovieTestCtrl'
	});
	
	$routeProvider.when('/type/:type', {
		templateUrl: 'views/catMovies.html',
		controller: 'showTypeController'
	});
	
    $routeProvider.when('/tvshow/:id', {
		templateUrl: 'views/tvshow.html',
		controller: 'tvshowController'
	});
	
	$routeProvider.when('/login', {
		templateUrl: 'views/login.html',
		controller: 'LoginCtrl'
	});
    
    $routeProvider.when('/register', {
		templateUrl: 'views/register.html',
		controller: 'registerController'
	});
    
    $routeProvider.when('/mywatchlist', {
		templateUrl: 'views/mywatchlist.html',
		controller: 'mywatchlistController'
	});
    
    $routeProvider.when('/tvshow/:id/season/:season_number', {
		templateUrl: 'views/season.html',
		controller: 'seasonController'
	});
    
    $routeProvider.when('/statistics', {
		templateUrl: 'views/statistics.html',
		controller: 'statisticsController'
	}); 
    
    $routeProvider.when('/search', {
		templateUrl: 'views/catMovies.html',
		controller: 'showTypeController'
	});
	
	$routeProvider.otherwise({
		redirectTo : '/'
	});
	
	
}]);