TvShowsApp.controller('mywatchlistController', ['$scope', 'getWatchlistService', 'removeFromWatchlistService', '$cookies', '$location',
                                                 '$rootScope',
function($scope, getWatchlistService, removeFromWatchlistService, $cookies, $location, $rootScope){
	$scope.results = [];
	$scope.loading = true;
	
	if ($rootScope.loginAction == false) {
       
          $location.path("/login");
       
      }
	else{
	
		var checkIfLoggedIn = function(){
	        
	        var token = $cookies.get('token');
	        
	        if (angular.isDefined(token)){
	            
	            $scope.username = "";
	            
	            for (var i=1; i<token.length; i++){
	                  
	                if (token[i] == ':')
	                    break;
	                
	                $scope.username += token[i];
	              
	            }
	            
	            return true;
	        }
	        else
	            return false;
	    };
		
		if(!checkIfLoggedIn){
			$location.path('/login');
		}
	
	    
		
		
		var loadMovies = function(){
			getWatchlistService.query(function(data){
				$scope.results = data;
			});
			
			$scope.loading = false;
		};
		
		loadMovies();
		
		$scope.removeFromWatchlist = function(tvShow, index){
	  
		var result = confirm("Are you sure you want to remove '"+ tvShow.title +"' from your wathclist?");
			if(result){
				$scope.results.splice(index, 1);	
			  	  removeFromWatchlistService.remove($.param({
			            
			            tmdb_id: tvShow.tmdbId
			            
			      }));		
			}
		
	  	  
	    };
	
	}	

}]);