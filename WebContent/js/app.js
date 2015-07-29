var app = angular.module('CobWebApp', ['ngMaterial', 'ngRoute']);



//Config theme
app.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('blue')
        .primaryPalette('blue')
        .accentPalette('red');
});

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

app.controller('CobWebAppCtrl', ['$scope', '$mdSidenav', '$http', function($scope, $mdSidenav, $http) {
  console.log("ctrller fired");
  $http.get('http://localhost:8080/cobweb/api/newsfeed/device').success(function(data) {
    $scope.chunks = data;
  });

}]);

app.controller('LayoutCtrl', ['$scope', '$mdSidenav', '$http', function($scope, $mdSidenav, $http) {
  $scope.toggleMenu = function() {
      $mdSidenav('left').toggle();
  };

  $scope.close = function() {
    $mdSidenav('left').close();
  }
}]);
