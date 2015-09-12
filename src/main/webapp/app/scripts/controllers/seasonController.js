TvShowsApp.controller('seasonController', [ '$scope', '$routeParams', 'tvShowService',
     function($scope, $routeParams, tvShowService){
	    var id = $routeParams.id;
	    var season_num = $routeParams.season_number;
	    $scope.result = tvShowService.getSeason(id, season_num);
	    $scope.tvShow = tvShowService.getShowById(id);
}
]);