var app = angular.module('CobWebApp', ['ngRoute', 'angularMoment', 'ngDialog']);

app.config(function($routeProvider, $locationProvider) {
  // $locationProvider.html5Mode(true);

  $routeProvider
  .when('/news', {
    templateUrl: 'newsfeed.html',
    controller: 'CobWebAppCtrl'
  })
  .when('/user/:id', {
    templateUrl: 'user.html',
    controller: 'UserCtrl'
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
    $rootScope.dataLoaded = false;
    $http.get('/api/device/newsfeed').success(function(data) {
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
    $http.get('/api/sensor/newsfeed').success(function(data) {
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
    $http.get('/api/' + type +'/' + obj.id).success(function(data) {
      obj.name = data.name;
      obj.type = data[type+'Type'];
    });
  }

  function loadDeviceData(obj){
    $http.get('/api/device/' + obj.deviceId).success(function(data) {
      obj.deviceName = data.name;
      obj.deviceType = data.deviceType;
    });
  }

  function loadUserData(obj){
    $http.get('/api/friends/' + obj.userId).success(function(data) {
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
    $rootScope.dataLoaded = true;
  }, true);

  $scope.$watch('sensorLst', function(newval, old){
    newval.forEach(function(obj){
      loadDataById(obj, 'sensor');
      loadUserData(obj);
      loadDeviceData(obj);
    });
    console.log(newval);
    $rootScope.dataLoaded = true;
  }, true);

  loadNews();
}]);

app.controller('LayoutCtrl', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location) {
  console.log("layout ctrller fired");
  $scope.logout = function(){
    console.log("logging out");
    $http.get('/api/logout').then(function(){
      $location.url('/');
    });
  }

}]);

app.controller('UserCtrl', ['$scope', '$http', '$routeParams', '$rootScope', function($scope, $http, $routeParams, $rootScope) {

  var userId = $routeParams.id;

  $scope.user = {
    userId: userId,
    userName: '',
    emailHash: ''
  };

  $scope.items = [];

  function loadUserData(obj){
    $http.get('/api/friends/' + obj.userId).success(function(data) {
      obj.userName = data.firstName + ' ' + data.lastName;
      obj.emailHash = data.emailHash;
      $rootScope.dataLoaded = true;
    });
  }

  function loadMyData(obj){
    $http.get('/api/user').success(function(data) {
      obj.userName = data.firstName + ' ' + data.lastName;
      obj.emailHash = data.emailHash;
      $rootScope.dataLoaded = true;
    });
  }

  function loadMyDevices(cb){
    $http.get('/api/device').success(function(devlst) {
      var deviceLst = [];
      devlst.forEach(function(device){
        var obj = {
          device: {name: device.name,
            id: device.id,
            type: device.deviceType,
            description: device.description
          },
          sensorIdList: device.sensorIdList,
          sensors: []
        };

        deviceLst.push(obj)
      });
      cb(deviceLst);
    });
  }

  function loadUserDevices(id, cb){
    $http.get('/api/friends/device/' + id).success(function(devlst) {
      var deviceLst = [];
      devlst.forEach(function(device){
        var obj = {
          device: {name: device.name,
            id: device.id,
            type: device.deviceType,
            description: device.description
          },
          sensorIdList: device.sensorIdList,
          sensors: []
        };

        deviceLst.push(obj)
      });
      cb(deviceLst);
    });
  }

  function loadUserSensors(lst){
    lst.forEach(function(device){
      device.sensorIdList.forEach(function(sensorId){
        $http.get('/api/sensor/' + sensorId).success(function(sensorData) {
          device.sensors.push(sensorData);
        });
        $scope.items.push(device);
      });
    });
    $scope.loadDeviceList(lst[0].device.name); //Show the details of first device. Find a better way.
  }

  $scope.loadDeviceList = function(deviceName){
    $scope.deviceVisible = true;
    $scope.sensorVisible = false;
    $scope.selectedItem = _.filter($scope.items, {device: {name: deviceName}});
  }

  $scope.loadSensorList = function(sensorName) {
    var sensorList = [];
    $scope.sensorVisible = true;
    $scope.deviceVisible = false;
    $scope.selectedItem.forEach(function(device){
      device.sensors.forEach(function(sensor){
        sensorList.push(sensor);
      });
    });
    $scope.selectedItem = _.filter(sensorList, {name:sensorName});
  }

  if (userId === "0") {
    loadMyData($scope.user);
    loadMyDevices(function(deviceLst){
      loadUserSensors(deviceLst)
    });
  } else {
    loadUserData($scope.user);
    loadUserDevices($scope.user.userId, function(deviceLst){
      loadUserSensors(deviceLst)
    });
  }

}]);

