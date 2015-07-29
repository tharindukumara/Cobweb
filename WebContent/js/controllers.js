// Main Controller
app.controller('CobWebAppCtrl', ['$scope', '$mdSidenav', '$http', function($scope, $mdSidenav, $http) {
    $scope.toggleMenu = function() {
        $mdSidenav('left').toggle();
    };
    $http.get('http://localhost:8080/cobweb/api/newsfeed/device').success(function(data) {
	    $scope.chunks = data;
	  });
}]);



// Left Sidebar Controller
app.controller('LeftCtrl', function($scope, $timeout, $mdSidenav) {
    $scope.close = function() {
        $mdSidenav('left').close();
    };
});
