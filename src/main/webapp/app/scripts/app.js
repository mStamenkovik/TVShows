/**
 * 
 */

var TvShowsApp = angular.module('tvShowsApp', ['ngResource', 'ngRoute', 'ngCookies', 'angular-svg-round-progress', 'toaster','LocalStorageModule', 'ui.bootstrap', 'angular-loading-bar']);

TvShowsApp.config(['$httpProvider', 'settings', 
		function($httpProvider, settings) {

			$httpProvider.interceptors.push('HRHttpInterceptors');
		}])
		
TvShowsApp.run(['$location', '$rootScope', function($location, $rootScope){
	$rootScope.$on( "$routeChangeStart", function(event, next, current) {
	      if ($rootScope.loginAction == null || $rootScope.loginAction == false) {
	        // no logged user, redirect to /login
	        /*if ( next.templateUrl === "/login.html") {
	        } else {*/
	          $location.path("/login");
	        //}
	      }
	    });
}]);