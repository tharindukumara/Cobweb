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

  $scope.deviceLst = [];
  $scope.sensorLst = [];

  function loadNews(){
    loadDevices();
    loadSensors();
  }

  function loadDevices(){
    $rootScope.newsLoaded = false;
    $http.get('http://localhost:8080/cobweb/api/device/newsfeed').success(function(data) {
      data.forEach(function(device){

        dev = {
          id: '',
          name: '',
          time: '',
          dp: '',
          msg : ''
        };

        dev.id = device.deviceId;
        dev.time = device.dateTime;
        dev.msg = device.message;
        $scope.deviceLst.push(dev);
      });
    });
  }

  function loadSensors(){
    $http.get('http://localhost:8080/cobweb/api/sensor/newsfeed').success(function(data) {
      data.forEach(function(sensor){

        sen = {
          id: '',
          deviceId: '',
          name: '',
          time: '',
          dp: '',
          msg : ''
        };

        sen.id = sensor.sensorId;
        sen.deviceId = sensor.deviceId;
        sen.time = sensor.timeStamp;
        sen.msg = sensor.message;
        $scope.sensorLst.push(sen);
      });
    });
  }

  function loadDataById(obj, type){
    $http.get('http://localhost:8080/cobweb/api/' + type +'/' + obj.id).success(function(data) {
      obj.name = data.name;
      obj.dp = data.imageUrl;
    });
  }

  function loadDeviceName(obj){
    $http.get('http://localhost:8080/cobweb/api/device/' + obj.deviceId).success(function(data) {
      obj.deviceName = data.name;
    });
  }

  // Ugly hack to update news. Better use promises
  $scope.$watch('deviceLst', function(newval, old){
    newval.forEach(function(obj){
      loadDataById(obj, 'device');
    });
    $rootScope.newsLoaded = true;
  }, true);

  $scope.$watch('sensorLst', function(newval, old){
    newval.forEach(function(obj){
      loadDataById(obj, 'sensor');
      loadDeviceName(obj);
    });
    console.log(newval);
    $rootScope.newsLoaded = true;
  }, true);

  loadNews();
}]);

app.controller('LayoutCtrl', ['$rootScope', function($rootScope) {
  console.log("layout ctrller fired");

}]);

