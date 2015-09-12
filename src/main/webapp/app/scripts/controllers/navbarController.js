


TvShowsApp.controller('navbarController', ['$scope', '$cookies', '$route', '$location', 'tvShowService', '$templateCache',
  function($scope, $cookies, $route, $location, $templateCache){
      
      $scope.searchKeyword = function(keyword){
    	 
    	  if(keyword != null && keyword != ''){	
    		  $scope.key = '';
    	      $location.path("/search").search({query: keyword});
          }
      };  
	
      $scope.checkIfLoggedIn = function(){
        
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
      }
      
      
      $scope.logout = function(){
        var loc = '/mywatchlist';
        var dest = '/';
        var currentPageTemplate = $route.current.templateUrl;
        $rootScope.loginAction = false;
        
          if (angular.isDefined($cookies.get('token'))){
                
              $cookies.remove('token');
              $templateCache.remove(currentPageTemplate);
              $route.reload();
          }
          
          
          
      }
      
         // $scope.loggedIn = $cookies.get('token');    
  }
]);