var app = angular.module('CobWebApp', ['ngRoute', 'angularMoment', 'ngDialog']);

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

app.controller('CobWebAppCtrl', ['$scope', '$http', '$rootScope', 'ngDialog', function($scope, $http, $rootScope, ngDialog) {
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
          userId: '',
          time: '',
          msg : ''
        };

        dev.id = device.deviceId;
        dev.userId = device.userId;
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
          userId: '',
          time: '',
          msg : ''
        };

        sen.id = sensor.sensorId;
        sen.userId = sensor.userId;
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
      obj.type = data[type+'Type'];
    });
  }

  function loadDeviceData(obj){
    $http.get('http://localhost:8080/cobweb/api/device/' + obj.deviceId).success(function(data) {
      obj.deviceName = data.name;
      obj.deviceType = data.deviceType;
    });
  }

  function loadUserData(obj){
    $http.get('http://localhost:8080/cobweb/api/friends/' + obj.userId).success(function(data) {
      obj.userName = data.firstName + ' ' + data.lastName;
      obj.emailHash = data.emailHash;
    });
  }

  $scope.popup = function (obj) {
    ngDialog.open({ template: '<h2>'+obj.userName+'</h2><p>Name: '+ obj.name+'</p>' +'<p>Id: '+ obj.id+'</p>'+'<p>Type: '+ obj.type+'</p>', className: 'ngdialog-theme-default', plain: true});
  };

  $scope.popupSensorParentDevice = function (obj) {
    ngDialog.open({ template: '<h2>'+obj.userName+'</h2><p>Name: '+ obj.deviceName+'</p>' +'<p>Id: '+ obj.deviceId+'</p>'+'<p>Type: '+ obj.deviceType+'</p>', className: 'ngdialog-theme-default', plain: true});
  };

  // Ugly hack to update news. Better use promises
  $scope.$watch('deviceLst', function(newval, old){
    newval.forEach(function(obj){
      loadDataById(obj, 'device');
      loadUserData(obj);
    });
    $rootScope.newsLoaded = true;
  }, true);

  $scope.$watch('sensorLst', function(newval, old){
    newval.forEach(function(obj){
      loadDataById(obj, 'sensor');
      loadUserData(obj);
      loadDeviceData(obj);
    });
    console.log(newval);
    $rootScope.newsLoaded = true;
  }, true);

  loadNews();
}]);

app.controller('LayoutCtrl', ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
  console.log("layout ctrller fired");
  $scope.logout = function(){
    console.log("logging out");
    $http.get('http://localhost:8080/cobweb/api/logout');
  }

}]);

