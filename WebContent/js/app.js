var app = angular.module('CobWebApp', ['ngRoute', 'angularMoment']);

app.config(function($routeProvider) {

  $routeProvider
  .when('/news', {
    templateUrl: 'newsfeed.html',
    controller: 'CobWebAppCtrl'
  })
  .when('/about', {
    templateUrl: 'views/about',
    controller: 'MetaCtrl'
  })
  .otherwise({
    templateUrl: 'views/404',
    controller: 'MetaCtrl'
  });
});

app.controller('CobWebAppCtrl', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
  console.log("cobwerbapp ctrller fired");

  $scope.loadNews = function() {
    $rootScope.newsLoaded = false;
    $http.get('http://localhost:8080/cobweb/api/device/newsfeed').success(function(data) {
      $scope.news = data;
      $rootScope.newsLoaded = true;
    });
  };

  $scope.loadNews();

}]);

app.controller('LayoutCtrl', ['$rootScope', function($rootScope) {
  console.log("layout ctrller fired");

}]);

