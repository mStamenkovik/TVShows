TvShowsApp.service('tvShowService',  ['$http', '$resource', function($http, $resource) {
    
    
	    var callback = 'JSON_CALLBACK';
	    var baseUrl = 'https://api.themoviedb.org/3/';
	    var apiKey = 'cbb012e4e7ece74ac4c32a77b00a43eb';
	    var url = baseUrl + 'tv/airing_today?' + apiKey ;
	    var tvShows = {};
		tvShows.data = {};
    
        var tmdbAPI = function(type){
           return $resource(baseUrl+type, 
                { callback: callback }, 
                      { get: { method: "JSONP" }}
        )};
        
         this.getShowsByType = function(type){
            
            var response = tmdbAPI("tv/"+type);
            return response.get({ api_key: apiKey });
            
        };
    
         this.getShowsByType = function(type, page_num){
            
            var response = tmdbAPI("tv/"+type);
            return response.get({ api_key: apiKey, page: page_num });
            
        };
        
        this.getShowById = function(id){
            
            var response = tmdbAPI("tv/"+id);
            return response.get({ api_key: apiKey });
            
        };
    
        this.getShowImages = function(id){
            
            var response = tmdbAPI("tv/"+id+"/images");
            return response.get({ api_key: apiKey });
        };
    
        this.getShowVideos = function(id){
            
            var response = tmdbAPI("tv/"+id + "/videos");
            return response.get({ api_key: apiKey });
            
        };
    
        this.getShowCredits = function(id){
            
            var response = tmdbAPI("tv/"+id + "/credits");
            return response.get({ api_key: apiKey });
            
        };
    
        this.getShowKeywords = function(id){
            
            var response = tmdbAPI("tv/" +id + "/keywords");
            return response.get({ api_key: apiKey });
            
        };
        
        this.getVideosById = function(id){
            
            var response = tmdbAPI("tv/" +id + "/videos");
            return response.get({ api_key: apiKey });
            
        };
    
        this.getSeason = function(id, season_num){
        	var response = tmdbAPI("tv/" + id + "/season/" + season_num);
        	return response.get({ api_key: apiKey });
        }
        
        this.getByKeyword = function(keyword, page_num){
        	var response = tmdbAPI("search/tv");
        	return response.get({ query: keyword, page: page_num, api_key: apiKey });
        }
        
}]);
