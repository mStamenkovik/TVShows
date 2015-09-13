/**
 * 
 */

var TvShowsApp = angular.module('tvShowsApp', ['ngResource', 'ngRoute', 'ngCookies', 'angular-svg-round-progress', 'toaster','LocalStorageModule', 'ui.bootstrap', 'angular-loading-bar']);

TvShowsApp.config(['$httpProvider', 'settings', 
		function($httpProvider, settings) {

			$httpProvider.interceptors.push('HRHttpInterceptors');
		}])
		
TvShowsApp.run(['$location', '$rootScope', '$cookies', function($location, $rootScope, $cookies){
	$rootScope.$on( "$routeChangeStart", function(event, next, current) {
		
      var checkIfLoggedIn = function(){        
	        var token = $cookies.get('token');
	        
	        if (angular.isDefined(token)){	            
	            return true;
	        }
	        else
	            return false;
	    };
		
		console.log("next.templateUrl -> " + next.templateUrl);
		console.log("current.templateUrl -> " + current.templateUrl);
		console.log("check rootscope: " + $rootScope.isLoggedIn);
		
		
			if(next.templateUrl == 'views/statistics.html' || next.templateUrl == 'views/mywatchlist.html'){
				  if(!checkIfLoggedIn()){
				        if ( next.templateUrl === "/login.html") {
				        } 
				        else {
				    	  $location.path("/login");
				        }	      
			  }
			}
		
	  });
}]);