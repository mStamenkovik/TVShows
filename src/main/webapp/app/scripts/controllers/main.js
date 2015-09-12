TvShowsApp.controller('MainCtrl', ['$scope', '$q', 'tvShowService', 'roundProgressService', '$interval', '$rootScope', '$window', 'localStorageService', '$cookies', '$http',
      function ($scope, $q, tvShowService, roundProgressService, $interval, $rootScope, $window, localStorageService, $cookies, $http) {

        $scope.result = tvShowService.getShowsByType('airing_today');

        $scope.id = {};

        $scope.show = {};

        $scope.id = $scope.result.$promise.then(function (data) {
            
            
            var id_show = data.results[1].id;
            
            $scope.id = id_show;

            $http.get('http://localhost:9966/tvshows/data/rest/shows/number/recommendations/' + id_show)
                .then(function (response) {

                    $scope.numOfRec = response.data;


                });



            console.log(data.results[0].id)

            $scope.show = tvShowService.getShowById(data.results[0].id);

        });






      }

]);