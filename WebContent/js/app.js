var app = angular.module('CobWebApp', ['ngRoute']);

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

app.controller('CobWebAppCtrl', ['$scope', '$http', function($scope, $http) {
  console.log("ctrller fired");
  $http.get('http://localhost:8080/cobweb/api/newsfeed/device').success(function(data) {
    $scope.chunks = data;
  });

}]);

