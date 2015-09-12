TvShowsApp.controller('showTypeController', ['$scope', '$routeParams', 'tvShowService',
   'recomendService', 'checkRecommendation', '$http', 'unRecommendService', '$cookies', 'watchlistService', 'removeFromWatchlistService',
  function($scope, $routeParams, tvShowService, recomendService,
		  checkRecommendation, $http, unRecommendService, $cookies, watchlistService, removeFromWatchlistService){
	$scope.result = [];
	$scope.message = '';
	$scope.loading = true;
    var type = $routeParams.type;
    
    if($routeParams.query != null && $routeParams.query != ''){
    	var keyword = $routeParams.query;
    	$scope.keyword = keyword;
    }
    
    
    $scope.currentPage = 1;
    $scope.totalPages = 0;
    
    var setShows = function(data){    	
    	$scope.result = data;
        $scope.totalPages = data.total_pages;             
        
            angular.forEach($scope.result.results, function(show) {
                     
              $http.get('http://localhost:9966/tvshows/data/rest/shows/number/recommendations/' + show.id)
                    .then(function(response){

                  show.numOfRec = response.data;                       
              });
                
                if ($scope.loggedIn){
                                       
                $http.get('http://localhost:9966/tvshows/data/rest/shows/recomend/check?tmdb_id=' + show.id)
                    .then(function(response){

                  show.recommendedByUser = response.data;
                 /* console.log(response.data);*/
              });

                $http.get('http://localhost:9966/tvshows/data/rest/shows/watchlist/check?tmdb_id=' + show.id)
                    .then(function(response){

                    show.addedToWatchlist = response.data;
            /*        console.log(response.data);*/
                });
                }

            });
            
            $scope.loading = false;
    };
    
    
    var loadShows = function(){
    	   tvShowService.getShowsByType(type, $scope.currentPage).$promise.then(function(data){
                setShows(data);
           });
    };
    
    var loadShowsByKeyword = function(keyword){
    	
    	tvShowService.getByKeyword(keyword, $scope.currentPage).$promise.then(function(data){
    		  setShows(data);
    	});
    	
    };
    
    $scope.pageChanged = function(){
        if(keyword){
        	loadShowsByKeyword(keyword);
        }
        else{	
        	loadShows();
        }
    };
    
    if(keyword){
    	loadShowsByKeyword(keyword);
    }
    else{	
    	loadShows();
    }
    
    if (angular.isDefined($cookies.get('token'))){
        $scope.loggedIn = true;   
      }
      else
          $scope.loggedIn = false;
      
     /* console.log('lgo in');
      console.log($scope.loggedIn);*/
      
 
      $scope.addToWatchlist = function(tvShow){
    	  
    	   angular.forEach($scope.result.results, function(show) {
               
               if (show.id == tvShow.id){
                   show.addedToWatchlist = true;
               }
               
           });
    	  
          var genres = tvShow.genre_ids[0];
          
          if (tvShow.genre_ids.length > 1);
          for (var i=1; i<tvShow.genre_ids.length; i++){
              genres += "," + tvShow.genre_ids[i];
          }
    	  
    	  watchlistService.addToWatchlist($.param({
                
                tmdb_id: tvShow.id,
                
                title: tvShow.name,
                
                overview: tvShow.overview,
                
                poster_path: tvShow.poster_path,
                
                genres: genres
                
                }));
    	  
      };
      	
      $scope.removeFromWatchlist = function(tvShow){
    	 
    	  angular.forEach($scope.result.results, function(show) {
              
              if (show.id == tvShow.id){
                  show.addedToWatchlist = false;
              }
              
          });

    	  removeFromWatchlistService.remove($.param({
              
              tmdb_id: tvShow.id
              
              }));
      };
      
      
      $scope.recomend = function(tvShow){
          
          angular.forEach($scope.result.results, function(show) {
                
              if (show.id == tvShow.id){
                  show.recommendedByUser = true;
              }
              
          });
          
          /*console.log(tvShow);
          
          console.log(tvShow.genre_ids);*/
          
          var genres = tvShow.genre_ids[0];
          
          if (tvShow.genre_ids.length > 1);
          for (var i=1; i<tvShow.genre_ids.length; i++){
              genres += "," + tvShow.genre_ids[i];
          }
          
        /*  console.log(genres);*/
          
          recomendService.recomend($.param({
              
              tmdb_id: tvShow.id,
              
              title: tvShow.name,
              
              overview: tvShow.overview,
              
              poster_path: tvShow.poster_path,
              
              genres: genres
              
              }));
              
          
      };
      
            
      $scope.unRecomend = function(tvShow){
          
          angular.forEach($scope.result.results, function(show) {
                
              if (show.id == tvShow.id){
                  show.recommendedByUser = false;
              }
              
          });

          unRecommendService.unRecommend($.param({
              
              tmdb_id: tvShow.id
              
              }));
                   
      };
      
        
      
  }
]);