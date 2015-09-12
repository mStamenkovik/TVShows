TvShowsApp.controller('LoginCtrl', [
		'$scope',
		'$rootScope',
		'$location',
		'$cookieStore',
		'UserService',
        'auth',
    '$window',
    '$resource',
    'localStorageService',
    '$cookies',

		function($scope, $rootScope, $location, $cookieStore, UserService, auth, $window, $resource, localStorageService, $cookies) {
			$scope.rememberMe = false;
            $scope.badCredentials = false;
			
            if (angular.isDefined($cookies.get('token'))){
                $location.path('/');
            }
            
            

			$scope.login = function() {
				UserService.authenticate($.param({
					username : $scope.username,
					password : $scope.password,
					rememberMe : $scope.rememberMe
				}), function(authenticationResult) {
                    console.log("Rezultati od logiranje");
                    console.log(authenticationResult);
					$rootScope.authToken = authenticationResult.token;
					$rootScope.loginAction = true;
                    //$window.sessionStorage.token = authenticationResult.token;
					if ($rootScope.returnPath
							&& $rootScope.returnPath != "/login") {
						$location.path($rootScope.returnPath);
						delete $rootScope.returnPath;
					} else {
						$location.path("/");
					}
				}, function(res){
					 if(res.status == 401){
						 $scope.badCredentials = true;
					 }
				});
			};
            
            
            
            
            
/*           $scope.login = function() { 
               
               var credentials = {};
               
               credentials.username = $scope.username;
               credentials.password = $scope.password;
               credentials.rememberMe = $scope.rememberMe;
               
                auth.login(credentials),
                function(result) {
                    
                   console.log("Rezultat od logiranje");
                   console.log(result);
               };
               
               
           };*/
            
            
            
            
            
            
		}   ]);