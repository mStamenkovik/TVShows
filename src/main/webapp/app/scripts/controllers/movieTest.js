TvShowsApp.controller('MovieTestCtrl', ['$scope', '$http',
     function($scope, $http){
		 var callback = 'JSON_CALLBACK';
		 var baseUrl = 'https://api.themoviedb.org/3/';
		 var apiKey = 'api_key=cbb012e4e7ece74ac4c32a77b00a43eb';
		 var url = baseUrl + 'movie/550?' + apiKey ;
		 
		 $http({method: 'GET', url: url})
		 .success(function(data){
			 $scope.result = data;
		 });

     }                               
]);



///http://api.themoviedb.org/3/movie/550?api_key=cbb012e4e7ece74ac4c32a77b00a43eb