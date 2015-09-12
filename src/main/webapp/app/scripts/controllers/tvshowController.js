TvShowsApp.controller('tvshowController', ['$scope', '$routeParams', 'tvShowService', '$cookies', '$http',
         'recomendService', 'unRecommendService', 'watchlistService', 'removeFromWatchlistService',
function($scope, $routeParams, tvShowService, $cookies, $http, recomendService, unRecommendService, watchlistService, removeFromWatchlistService){
    var id = $routeParams.id;
    $scope.result = tvShowService.getShowById(id);
    $scope.videos = tvShowService.getVideosById(id);
    $scope.credits = tvShowService.getShowCredits(id);
    
    var loadMovie = function(){
        $http.get('http://localhost:9966/tvshows/data/rest/shows/recomend/check?tmdb_id=' + id).then(function(response){
               $scope.recommendedByUser = response.data;
     /* console.log(response.data);*/
        });

    $http.get('http://localhost:9966/tvshows/data/rest/shows/watchlist/check?tmdb_id=' + id).then(function(response){
           $scope.addedToWatchlist = response.data;
/*        console.log(response.data);*/
       });
    };
    
    if (angular.isDefined($cookies.get('token'))){
        $scope.loggedIn = true;  
        loadMovie();
      }
      else
          $scope.loggedIn = false;
    
    
     $http.get('http://localhost:9966/tvshows/data/rest/shows/number/recommendations/' + id)
       .then(function(response){

     $scope.numOfRec = response.data;


    });
    
    
    
    
    
    $scope.addToWatchlist = function(tvShow){
  	              
       $scope.addedToWatchlist = true;
  	  
       var genres = tvShow.genres[0];
       
       if (tvShow.genres.length > 1);
       for (var i=1; i<tvShow.genres.length; i++){
           genres += "," + tvShow.genres[i].id;
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
  	 
       $scope.addedToWatchlist = false;

 	  removeFromWatchlistService.remove($.param({
           
           tmdb_id: tvShow.id
           
           }));
   };
   
   
   $scope.recomend = function(tvShow){
      
        $scope.recommendedByUser = true;
         
        var genres = tvShow.genres[0];
       
       if (tvShow.genres.length > 1);
       for (var i=1; i<tvShow.genres.length; i++){
           genres += "," + tvShow.genres[i].id;
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
       
       $scope.recommendedByUser = false;

       unRecommendService.unRecommend($.param({
           
           tmdb_id: tvShow.id
           
           }));
                
   };
    
    
    
}]);




