
'use strict';


TvShowsApp.constant('settings', {
  contextPath: "",
  dateFormats: ['DD-MM', 'DD-MM-YY', 'DD-MM-YYYY', 'DD-MM-YYYY HH:mm'],
  useAuthTokenHeader: true
});

TvShowsApp.value('version', '0.1');

// register the interceptor as a service

TvShowsApp.factory('HRHttpInterceptors', function($q, $location, $rootScope,
  $filter, toaster, settings, $cookies) {
  return {
    'request': function(config) {
    	
    	/*console.log("FUNCTION CONFIG");
    	console.log("config.url");
    	console.log(config.url);*/
      // Prepend context path to each request to absolute path
      if (config.url.length > 0) {
        if (config.url.charAt(0) == "/") {
        	/*console.log("ASDAS");
        	console.log("context " + settings.contextPath);*/
          config.url = settings.contextPath + config.url;
        }
      }
      // Add token in each request header
      
      /*console.log("AuthToken config");*/
      
      var authToken = $rootScope.authToken;
        
        if (!angular.isDefined(authToken)){
            authToken = $cookies.get('token');
        }
    /*  console.log(authToken);*/
      if (angular.isDefined(authToken)) {
        if (settings.useAuthTokenHeader) {
          config.headers['X-Auth-Token'] = authToken;
        } else {
          config.url = config.url + "?token=" + authToken;
        }
      }
      $q.when(console.log("when congif " + config));
      return config || $q.when(config);
    },
    'responseError': function(rejection) {
    	/*console.log("RESPONSE ERROR");
    	console.log(rejection);*/
      var status = rejection.status;
      if (status == 400) {
        // invalid data 

      }
      if (status == 401) {
          if (angular.isDefined($cookies.get('token')))
              $cookies.remove('token');
        if (!$rootScope.returnPath) {
          $rootScope.returnPath = $location.path();
        }
        $location.path("/login");
      }
      return $q.reject(rejection);
    }
  };
});