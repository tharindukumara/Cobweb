var myApp = angular.module('myApp', []);

myApp.controller('MyController', function MyController($scope, $http) {
    $http.get('http://localhost:8080/cobweb/api/newsfeed/sensor').success(function(data) {
        $scope.sensorNewsfeed = data;
    });
})