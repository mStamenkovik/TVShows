<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html ng-app="tvShowsApp">

    <head>
        <meta charset="utf-8">
        <title>TVShows</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- <meta name="viewport" content="width=device-width"> -->
        <meta charset="utf-8">
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <!-- build:css(.) styles/vendor.css -->
        <!-- bower:css -->
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css" />
        <link rel='stylesheet' href='bower_components/angular-loading-bar/build/loading-bar.css'/>
        <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap-theme.css" />
        
  <!--  <link rel="stylesheet" href="bower_components/AngularJS-Toaster/toaster.css" />
  <link rel="stylesheet" href="bower_components/angular-loading-bar/build/loading-bar.css" />
  <link rel="stylesheet" href="bower_components/angular-motion/dist/angular-motion.css" />
  <link rel="stylesheet" href="bower_components/fullcalendar/dist/fullcalendar.css" />
  <link rel="stylesheet" href="bower_components/select2/select2.css" />
  <link rel="stylesheet" href="bower_components/bootstrap-markdown/css/bootstrap-markdown.min.css" />
  <link rel="stylesheet" href="bower_components/datetimepicker/jquery.datetimepicker.css" />
  <link rel="stylesheet" href="bower_components/ng-table/ng-table.css" />
  <link rel="stylesheet" href="bower_components/ngQuickDate/dist/ng-quick-date.css" />
  <link rel="stylesheet" href="bower_components/ngQuickDate/dist/ng-quick-date-default-theme.css" /> -->
        <!-- endbower -->
        <!-- endbuild -->
        <!-- build:css(.tmp) styles/main.css -->
         <link rel="stylesheet" href="css/main.css"> 
        <!-- endbuild -->
        
        <style>
    body {
        padding-top: 50px;
        max-width: 100%;
        overflow-x: hidden;
         background-color: #73503c; 
    }
     .fontColorText{
        color: #DCDDCD !important;
    }
 
   .glow:focus {
    border-color: #D94E67;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),0 0 8px rgba(217, 78, 103, 0.6);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),0 0 8px rgba(217, 78, 103, 0.6);
}
</style>
        
    </head>

    <body ng-cloak>
        <nav class="navbar navbar-inverse navbar-fixed-top" ng-controller="navbarController">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a  href="#/" class="navbar-brand">Show-maniac</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    
  
                    <ul class="nav navbar-nav navbar-left">
                        <li>
                            <a href="#/type/popular">
                               Popular
                            </a>
                        </li>
                        <li><a href="#/type/top_rated">Top Rated</a>
                        </li>
                        <li><a href="#/type/airing_today">Airing Today</a>
                        </li>
                        <li><a href="#/type/on_the_air">On the air</a>
                        </li>
                        <li><a href="#/statistics">Statistics</a>
                        </li>    
                    </ul>
                    
                    <ul class="nav navbar-nav navbar-right">
                        <li ng-show="!checkIfLoggedIn()"><a href="#/register">Sign up</a></li>
                        <li ng-show="!checkIfLoggedIn()"><a href="#/login">Log in</a>
                        </li>
                        
                        
                          <li class="dropdown" ng-show="checkIfLoggedIn()">
                            <a class="dropdown-toggle " data-toggle="dropdown" >{{ username }} <span class="glyphicon glyphicon-user"></span><b class="caret"></b></a>
                          <ul class="dropdown-menu">
                             <li>
                                <a href="#/mywatchlist">My watchlist</a>
                             </li>
                             <li>
                                <a ng-click="logout()">Logout</a>
                             </li>
                          </ul>
                        </li>                   
                    </ul>   
                    
        <div class="col-sm-3 col-md-3 pull-right">
			<form ng-submit="searchKeyword(key)" class="navbar-form" role="search">
			<div class="input-group">
				<input type="text" class="form-control glow" placeholder="Search" ng-model="key" name="srch-term" id="srch-term">
                 <span class="input-group-addon"  ng-click="searchKeyword(key)" role="button">
                    <span id="filter" type="submit" >
                        <i class="glyphicon glyphicon-search"></i>
                    </span>
                </span>
                
			</div>
			</form>
		</div>
                    
                </div>
            </div>
            <!-- end container-fluid -->
        </nav>
        
        
            <div ng-view>
            </div>
        <!-- build:js(.) scripts/vendor.js -->
        <!-- bower:js -->
        <script src="bower_components/jquery/dist/jquery.js"></script>
        <script src="bower_components/angular/angular.js"></script>
        <script src="bower_components/angular-route/angular-route.js"></script>
        <script src="bower_components/angular-resource/angular-resource.js"></script>
        <script src="bower_components/angular-cookies/angular-cookies.js"></script>
         <script src="bower_components/angular-svg-round-progressbar/build/roundProgress.min.js"></script>
         <script src="bower_components/angularjs-toaster/toaster.js"></script>
         <script src="bower_components/angular-local-storage/dist/angular-local-storage.min.js"></script>
          <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
		 <script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
        <script src="bower_components/Chart.js/Chart.min.js"></script>
        <script src='bower_components/angular-loading-bar/build/loading-bar.min.js'></script>
		 <script src="bower_components/angular-animate/angular-animate.js"></script>	
			                      
        <!--
  <script src="bower_components/angular-sanitize/angular-sanitize.js"></script>
  <script src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
  <script src="bower_components/jquery-cookie/jquery.cookie.js"></script>
  <script src="bower_components/jquery-ui/jquery-ui.js"></script>
  <script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
  <script src="bower_components/angular-strap/dist/angular-strap.min.js"></script>
  <script src="bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
  <script src="bower_components/AngularJS-Toaster/toaster.js"></script>
  <script src="bower_components/angular-loading-bar/build/loading-bar.js"></script>
  <script src="bower_components/angular-translate/angular-translate.js"></script>
  <script src="bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js"></script>
  <script src="bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
  <script src="bower_components/moment/moment.js"></script>
  <script src="bower_components/fullcalendar/dist/fullcalendar.js"></script>
  <script src="bower_components/angular-ui-calendar/src/calendar.js"></script>
  <script src="bower_components/select2/select2.js"></script>
  <script src="bower_components/angular-ui-select2/src/select2.js"></script>
  <script src="bower_components/bootstrap-markdown/js/bootstrap-markdown.js"></script>
  <script src="bower_components/datetimepicker/jquery.datetimepicker.js"></script>
  <script src="bower_components/jquery-textcomplete/dist/jquery.textcomplete.min.js"></script>
  <script src="bower_components/momentjs/moment.js"></script>
  <script src="bower_components/ng-file-upload/angular-file-upload.js"></script>
  <script src="bower_components/ng-table/ng-table.js"></script>
  <script src="bower_components/ng-table-export/ng-table-export.js"></script>
  <script src="bower_components/ngQuickDate/dist/ng-quick-date.js"></script> -->
        <!-- endbower -->
        <!-- endbuild -->

        <!-- These scripts hold the code of the application -->
        <!-- build:js({.tmp,app}) scripts/scripts.js -->
        <!-- The definition and the configuration of the application module -->

        <script src="scripts/app.js"></script>
        <!-- The route configuration -->
        <script src="scripts/router.js"></script>
        <script src="scripts/config.js"></script>
        <script src="scripts/forCarousel.js"></script>
        
        <!--  directives  -->
        <script src="scripts/directives/youtubeDirective.js"></script>
        <script src="scripts/directives/noImageDirective.js"></script>
        
        <!-- controllers definition -->
        <script src="scripts/controllers/main.js"></script>
        <script src="scripts/controllers/movieTest.js"></script>
        <script src="scripts/controllers/showTypeController.js"></script>
        <script src="scripts/controllers/login.js"></script>
        <script src="scripts/controllers/tvshowController.js"></script>
        <script src="scripts/controllers/navbarController.js"></script>
        <script src="scripts/controllers/registerController.js"></script>
        <script src="scripts/controllers/seasonController.js"></script>
        <script src="scripts/controllers/mywatchlistController.js"></script>
        <script src="scripts/controllers/statisticsController.js"></script>
        
        <!-- Services definition -->
        <script src="scripts/services/services.js"></script>
        <script src="scripts/services/tvShowService.js"></script>
        <script src="scripts/services/user.js"></script>
         <script src="scripts/services/auth.js"></script>
         <script src="scripts/services/recomend.js"></script>
         
        <!-- Filters definition --> 
         <script src="scripts/filters/range.js"></script>
         
        <!-- endbuild -->
    </body>

    </html>