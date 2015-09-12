TvShowsApp.controller('mywatchlistController', ['$scope', 'getWatchlistService', 'removeFromWatchlistService', '$cookies', '$location',
function($scope, getWatchlistService, removeFromWatchlistService, $cookies, $location){
	$scope.results = [];
	$scope.loading = true;
	
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
  
	$scope.results.splice(index, 1);	
  	  removeFromWatchlistService.remove($.param({
            
            tmdb_id: tvShow.tmdbId
            
            }));
  	  
    };
	
	
	
	
}                                                
]);