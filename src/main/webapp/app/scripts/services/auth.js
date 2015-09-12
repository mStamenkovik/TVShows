'use strict';

TvShowsApp.factory("auth",['localStorageService', 'UserService', function(localStorageService, UserService) {
        return {
            login: function(credentials) {
                console.log("TUKA: " + credentials);
               var t = UserService.authenticate($.param({
					username : credentials.username,
					password : credentials.password,
					rememberMe : credentials.rememberMe
				}), function (response) {
                    console.log("ODGOVOR");
                    localStorageService.set('token', response);
                    console.log(response);
                    console.log(localStorageService.get('token'));
                    console.log(localStorageService);
                    console.log("KRAJ ODGOVOR");
                    return response;
                });
                
                return t;
            },
            logout: function() {
                //Stateless API : No server logout
                localStorageService.clearAll();
            },
            getToken: function () {
                return localStorageService.get('token');
            },
            hasValidToken: function () {
                var token = this.getToken();
                return token && token.expires && token.expires > new Date().getTime();
            }
        };
}]);

