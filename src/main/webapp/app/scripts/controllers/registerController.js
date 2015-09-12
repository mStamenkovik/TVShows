TvShowsApp.controller('registerController', [
  '$scope',
  '$rootScope',
  '$location',
  '$cookieStore',
  'UserService',
    '$resource',
    '$cookies',
    '$http',

  function ($scope, $rootScope, $location, $cookieStore, UserService, $resource, $cookies, $http) {
        $scope.rememberMe = false;

        if (angular.isDefined($cookies.get('token'))) {
            $location.path('/');
        }



        $scope.register = function () {

            if ($scope.registrationForm.$valid) {

                if (angular.isDefined($scope.samePass)) {
                    if ($scope.samePass == false)
                        alert('Password Fields dont match');
                    else {
                        console.log('sending data so server');
                        UserService.register($.param({
                            username: $scope.username,
                            password: $scope.password,
                            firstname: $scope.firstname,
                            lastname: $scope.lastname,
                            email: $scope.email,
                        }), function (registerResult) {
                            console.log("Rezultati od registarcija");
                            $location.path("/");
                        });
                    }
                }

            }

        };

        $scope.passConf = function () {

            if ($scope.password == $scope.passwordConf)
                $scope.samePass = true;
            else
                $scope.samePass = false;

        }

        $scope.functionExecuted = false;

        $scope.checkUsername = function (username) {

            $http.get('http://localhost:9966/tvshows/data/rest/user/username?username=' + username).then(function (response) {


                $scope.functionExecuted = true;
                if (response.data == true)
                    $scope.taken = true;
                else
                    $scope.taken = false;
            });


        };



  }]);