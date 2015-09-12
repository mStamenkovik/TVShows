TvShowsApp.controller('statisticsController', ['$scope', 'getWatchlistService', '$q', '$http', 
               function ($scope, getWatchlistService, $q, $http) {

	   $scope.myRec = false;
	   $scope.myChart = false;

        $http.get('/tvshows/data/rest/shows/watchlist/all')
        .then(function (response) {

            genreNames = {}

            response.data.forEach(function (show) {
                genreIds = show.genres.split(",");
                for (i = 0; i < genreIds.length; i++) {


                    if (genreIds[i] in genres) {

                        name = genres[genreIds[i]];
                        if (name in genreNames) {
                            genreNames[name] ++;
                        } else {
                            genreNames[name] = 1;
                        }

                    }

                }



            });

            

            var ctx = document.getElementById("allChart").getContext("2d");
            defData(genreNames, ctx, 'PolarArea');
        });
    
    
    ///////
    
            $http.get('/tvshows/data/rest/shows/recomended/all')
        .then(function (response) {

            genreNames = {}

            response.data.forEach(function (show) {
                genreIds = show.genres.split(",");
                for (i = 0; i < genreIds.length; i++) {


                    if (genreIds[i] in genres) {

                        name = genres[genreIds[i]];
                        if (name in genreNames) {
                            genreNames[name] ++;
                        } else {
                            genreNames[name] = 1;
                        }

                    }

                }



            });

            

            var ctx = document.getElementById("allRec").getContext("2d");
            defData(genreNames, ctx, 'Pie');
        });
    
    
    
                $http.get('/tvshows/data/rest/shows/getRecomended')
        .then(function (response) {

            genreNames = {}

            response.data.forEach(function (show) {
                genreIds = show.genres.split(",");
                for (i = 0; i < genreIds.length; i++) {


                    if (genreIds[i] in genres) {

                        name = genres[genreIds[i]];
                        if (name in genreNames) {
                            genreNames[name] ++;
                        } else {
                            genreNames[name] = 1;
                        }

                    }

                }



            });

            if(response.data.length == 0){
            	$scope.myRec = true;
            }

            var ctx = document.getElementById("myRec").getContext("2d");
            defData(genreNames, ctx, 'Pie');
        });
    
    
    ///////
    
    
    
    
    $http.get('/tvshows/data/rest/shows/getWatchlist')
        .then(function (response) {

            genreNames = {}

            response.data.forEach(function (show) {

                genreIds = show.genres.split(",");
                for (i = 0; i < genreIds.length; i++) {


                    if (genreIds[i] in genres) {

                        name = genres[genreIds[i]];
                        if (name in genreNames) {
                            genreNames[name] ++;
                        } else {
                            genreNames[name] = 1;
                        }

                    }

                }



            });

            if(response.data.length == 0){
            	$scope.myChart = true;
            }
            //$scope.results = genreNames;
            var ctx = document.getElementById("myChart").getContext("2d");
            defData(genreNames, ctx, 'PolarArea');

        });

    defData = function (genreNames, ctx, type) {
        console.log("se povikuva");
        data = []


        for (var key in genreNames) {
            console.log(key, genreNames[key]);

            color = getRandomColor();

            chartObj = {}
            chartObj['value'] = genreNames[key];
            chartObj['color'] = color;
            chartObj['highlight'] = lightenDarkenColor(color, -20);
            chartObj['label'] = key;


            data.push(chartObj);
        }

        $scope.results = data;
        
        if (type == 'PolarArea'){
        
        var myLineChart = new Chart(ctx).PolarArea(data, {
            bezierCurve: false
        });
            
        }
    
        if (type == 'Pie'){
            var myLineChart = new Chart(ctx).Pie(data, {
            bezierCurve: false
        });
        }


    }



    function componentToHex(c) {
        var hex = c.toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    }

    function rgbToHex(r, g, b) {
        return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
    }

    function getRandomColor() {

        colors = ['#FF69B4', '#993f6c', '#ff96ca', '#ffc3e1', '#e55ea2', '#4c1f36', '#69ffb4', '#b4ff69', '#69b4ff', '#ff6969', '#cc5454', '#ffa5a5', '#f89dbe',
                 '#f094d8', '#f7968c', '#f59f9f', '#ddf19d', '#c2f7a3', '#c1fa9d', '#a5cdef', '#a3baf7', '#793c51', '#624c45', '#79484d'];

        return colors[Math.floor(Math.random() * colors.length)];
    }

    var lightenDarkenColor = function (col, amt) {
        var usePound = false;
        if (col[0] == "#") {
            col = col.slice(1);
            usePound = true;
        }
        var num = parseInt(col, 16);
        var r = (num >> 16) + amt;
        if (r > 255) {
            r = 255;
        } else if (r < 0) {
            r = 0;
        }
        var b = ((num >> 8) & 0x00FF) + amt;
        if (b > 255) {
            b = 255;
        } else if (b < 0) {
            b = 0;
        }
        var g = (num & 0x0000FF) + amt;
        if (g > 255) {
            g = 255;
        } else if (g < 0) {
            g = 0;
        }
        return (usePound ? "#" : "") + (g | (b << 8) | (r << 16)).toString(16);
    }





    genres = {};
    genres['28'] = 'Action';
    genres['12'] = 'Adventure';

    genres['16'] = 'Animation';
    genres['35'] = 'Comedy';
    genres['80'] = 'Crime';
    genres['99'] = 'Documentary';

    genres['18'] = 'Drama';
    genres['10751'] = 'Family';
    genres['14'] = 'Fantasy';
    genres['10769'] = 'Foreign';
    genres['36'] = 'History';
    genres['27'] = 'Horror';
    genres['10402'] = 'Music';
    genres['9648'] = 'Mystery';
    genres['10749'] = 'Romance';
    genres['878'] = 'Science Fiction';
    genres['10770'] = 'TV Movie';
    genres['53'] = 'Thriller';
    genres['10752'] = 'War';
    genres['37'] = 'Western';
    genres['10765'] = 'Sci-Fi & Fantasy';



}]);