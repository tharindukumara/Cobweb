google.load('visualization', '1', {packages:['corechart']});

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
  .when('/notifications', {
    templateUrl: 'notifications.html',
    controller: 'NotificationCtrl'
  })
  .when('/dashboard', {
    templateUrl: 'dashboard.html',
    controller: 'DashboardCtrl'
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
      console.log(data);
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

app.controller('LayoutCtrl', ['$rootScope', '$scope', '$http', '$location', '$window', '$filter', function($rootScope, $scope, $http, $location, $window, $filter) {
  console.log("layout ctrller fired");
  var baseLen = $location.absUrl().length - $location.url().length - 'web/home.html#'.length;
  var rediretUrl = $location.absUrl().slice(0, baseLen);
  $scope.logout = function(){
    console.log("logging out", rediretUrl);
    $http.get('/api/logout').then(function(){
      $window.location.href=rediretUrl;
    });
  }

  /*
  Search
  */

  function loadUserList(cb) {
    $http.get('/api/user/search').success(function(userlst) {
      $scope.items = userlst;
      cb();
    });
  }

  loadUserList(afterload);

  function afterload(){
    var opened = 'opened';
    var closed = 'closed';

    $scope.selected = '';
    $scope.created = false;
    $scope.state = closed;
    $scope.change = function () {
      var filtered;
      filtered = $filter('filter')($scope.items, $scope.query);
      return $scope.state = filtered.length > 0 ? opened : 'closed';
    };
  }

  $scope.gotoProfile = function(id){
    console.log(id);
    $location.url('/user/' + id);
  }

}]);

app.controller('UserCtrl', ['$scope', '$http', '$routeParams', '$rootScope', 'ngDialog', '$location', function($scope, $http, $routeParams, $rootScope, ngDialog, $location) {

  var userId = $routeParams.id;

  $scope.user = {
    userId: userId,
    userName: '',
    emailHash: ''
  };

  $scope.friendRequests = [];

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
            description: device.description,
            subscribed: false
          },
          sensorIdList: device.sensorIdList,
          sensors: []
        };

        deviceLst.push(obj)
      });
      cb(deviceLst);
    });
  }

  function loadMySensors(lst){
    $scope.items = [];
    $http.get('/api/sensor').success(function(sensors) {
      var parentDevice;
      lst.forEach(function(item){
        sensors.forEach(function(sensor){
          if (item.device.id == sensor.parentDeviceId){
            item.sensors.push(sensor);  //O(D*S) :(
          }
        });
      $scope.items.push(item);
      });
      console.log('scope items', $scope.items);
      $scope.loadDeviceList(lst[0].device.name);
    });
  }

  function loadUserSensors(lst){
    $scope.items = [];
    lst.forEach(function(device){
      device.sensorIdList.forEach(function(sensorId){
        $http.get('/api/sensor/' + sensorId).success(function(sensorData) {
          device.sensors.push(sensorData);
        });
        $scope.items.push(device);
      });
    });
    $scope.loadDeviceList(lst[0].device.name); //Show the details of first device. Find a better way.
    setSubscribedStatus(lst);
    $rootScope.dataLoaded = true;
  }

  function deviceSubscribeStatus(lst, cb) {
    $http.get('/api/device/subscriptions').success(function(subscriptions) {
      lst.forEach(function(item){
        if (subscriptions.indexOf(item.device.id) > -1){
          item.device.subscribed = true;
        } else {
          item.device.subscribed = false;
        }
      });
      cb(lst);
    });
  }

  function sensorSubscribedStatus(lst){
    $http.get('/api/sensor/subscriptions').success(function(subscriptions) {
      lst.forEach(function(item){
        item.sensors.forEach(function(sensor){
          if (subscriptions.indexOf(sensor.id) > -1) {
            sensor.subscribed = true;
          } else {
            sensor.subscribed = false;
          }
          console.log(sensor);
        });
      });
    });
  }

  function setSubscribedStatus(lst){
    deviceSubscribeStatus(lst, sensorSubscribedStatus);
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

  $scope.loadDeviceCoapKey = function(obj) {
    console.log(obj);
    $http.get('/api/device/key/'+obj.id).success(function(key) {
      obj.coapKey = key;
    });
  }

  $scope.loadSensorCoapKey = function(obj) {
    console.log(obj);
    $http.get('/api/sensor/key/'+obj.id).success(function(key) {
      obj.coapKey = key;
    });
  }

  function loadDeviceCards(id) {
    $scope.cardLst = [];

    $http.get('/api/device/message/' + id).success(function(data) {
      data.forEach(function(device){

        dev = {
          id: '',
          payload_id: '',
          name: '',
          userId: '',
          time: '',
          msg: '',
          item: 'device'
        };

        dev.id = device.deviceId;
        dev.payload_id = device.id;
        dev.userId = device.userId;
        dev.time = device.dateTime;
        dev.msg = device.message;
        $scope.cardLst.push(dev);
      });
      console.log($scope.cardLst);
    });
  }

  function loadSensorCards(id){
    $scope.cardLst = [];

    $http.get('/api/sensor/message/' + id).success(function(data) {
      data.forEach(function(sensor){

        sen = {
          id: '',
          deviceId: '',
          payload_id: '',
          name: '',
          userId: '',
          time: '',
          msg: '',
          item: 'sensor'
        };

        sen.id = sensor.sensorId;
        sen.userId = sensor.userId;
        sen.deviceId = sensor.deviceId;
        sen.payload_id = sensor.id;
        sen.time = sensor.timeStamp;
        sen.msg = sensor.message;
        $scope.cardLst.push(sen);
      });
      console.log($scope.cardLst);
    });
  }

  $scope.unfriend = function(){
    console.log("Unfriend", $scope.user.userId);
    $http.delete('/api/friends', {data: $scope.user.userId}).success(function(res){
      console.log(res);
    });
  }

  $scope.confirmUnfriend = function(){
    ngDialog.open({ template: '<p>Are you sure you want to unfriend ' + $scope.user.userName + '?</p> <button ng-click="unfriend(); closeThisDialog()" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Yes</button>', className: 'ngdialog-theme-default', scope: $scope, plain: true});
  }

  $scope.popup = function (obj) {
    ngDialog.open({ template: '<h2>'+obj.userName+'</h2><p>Name: '+ obj.name+'</p>' +'<p>Id: '+ obj.id+'</p>'+'<p>Type: '+ obj.type+'</p>', className: 'ngdialog-theme-default', plain: true});
  };

  $scope.subscribe = function(device, type){
    $http({
      method: 'POST',
      url: '/api/' + type +'/subscriptions',
      data: device.id,
      headers: {
        'Content-Type': 'text/plain'
      }})
      .success(function(result) {
        console.log(result);
      });
    device.subscribed = true;
  };

  $scope.unsubscribe = function(device, type){
    $http.delete('/api/' + type +'/subscriptions', {data: device.id}).success(function(res){
      console.log(res);
    });
    device.subscribed = false;
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

  $scope.deleteItem = function(){
    console.log("delete item", $scope.deleteId, $scope.deleteType);
    if ($scope.deleteType == 'device'){
      $http.delete('/api/device', {data: $scope.deleteId}).success(function(res){
        _.remove($scope.items, function(item){
          return item.device.id == $scope.deleteId;
        });
      });
    } else if ($scope.deleteType == 'sensor'){
      $http.delete('/api/sensor', {data: $scope.deleteId}).success(function(res){
        $scope.items.forEach(function(item){
          _.remove(item.sensors, function(sensor){
            return sensor.id == $scope.deleteId;
          });
        });
      });
    }
  }

  $scope.confirm = function(name, id, type){;
    $scope.deleteId = id;
    $scope.deleteType = type;
    ngDialog.open({ template: '<p> You sure you want to delete '+ name +'?</p> <button ng-click="deleteItem(); closeThisDialog()" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent">Delete</button>', className: 'ngdialog-theme-default', scope: $scope, plain: true});
  }


  $scope.loadCards = function(id, type){
    if (id){
      if (type == 'device') {
        loadDeviceCards(id);
      } else if (type == 'sensor') {
        loadSensorCards(id);
      }
    }
  }

  /*
  Create New Item
  */

  $scope.createItemPopup = function(){
    ngDialog.open({ template: 'createItemTemplate.html', className: 'ngdialog-theme-default', scope: $scope});
  }

  $scope.createItemType = 'device';
  $scope.deviceTypes = ['IPHONE','ANDROIDPHONE','WINDOWSPHONE','BLACKBERRYPHONE','ARDUINO','RASPBERRYPI','BEAGLEBONE','BEAGLEBOARD','INTELEDISON','INTELGALILEO','PC','GADGETEER','OTHER'];
  $scope.sensorTypes = ['GPS','TEMPERATURE','PRESSURE','HUMIDITY','GAS','ACCELEROMETER','GYRO','COMPASS','PROXIMITY','LUMINOSITY','POTENTIOMETER','PUSHBUTTON','TOUCH','OTHER'];

  $scope.createDevice = function(device){

    if (device.type != 'OTHER'){
      device.other = "null";
    }
    var dev = {
      name: device.name,
      description: device.description,
      type: device.type,
      otherType: device.other
    };

    $http.post('/api/device', dev).success(function(id) {
      loadMyDevices(function(deviceLst){
        loadMySensors(deviceLst);
      });
    });
  };

  $scope.createSensor = function(sensor){
    if (sensor.type != 'OTHER'){
      sensor.other = "null";
    }
    var sen = {
      name: sensor.name,
      description: sensor.description,
      type: sensor.type,
      otherType: sensor.other,
      deviceId: sensor.parentDeviceId
    };
    $http.post('/api/sensor', sen).success(function(id) {
      loadMyDevices(function(deviceLst){
        loadMySensors(deviceLst);
      });
    });
  };

  /*
  /Create New Item
  */

  /*
  Followers popup
  */

  function loadFollowerName(id, cb){
    $http.get('/api/friends/' + id).success(function(data) {
      user = {
        name: data.firstName + ' ' + data.lastName,
        id: id
      };
      cb(user);
    });
  }

  $scope.subscriberPopup = function(id, type){
    $scope.followers = [];
    $http.get('/api/'+ type +'/subscribers/' + id).success(function(ids) {
      console.log(ids);
      ids.forEach(function(id){
        loadFollowerName(id, function(data){
          console.log(data);
          $scope.followers.push(data);
        });
      });
      ngDialog.open({ template: 'followers.html', className: 'ngdialog-theme-default', scope: $scope});
    });
  }

  /*
  /Followers popup
  */

  /*
  New Message popup
  */
  $scope.newMessagePopup = function(){
    ngDialog.open({ template: 'newMessageTemplate.html', className: 'ngdialog-theme-default', scope: $scope});
  }

  $scope.postMessage = function(message){
    console.log(message);
  }

  $scope.$watch('cardLst', function(newval, old){
    console.log(newval);
    if (newval !== undefined && newval.length !== 0){
      if (newval[0].item == 'device'){
        newval.forEach(function(obj){
          loadDataById(obj, 'device');
          loadUserData(obj);
        });
      } else if (newval[0].item == 'sensor'){
        newval.forEach(function(obj){
          loadDataById(obj, 'sensor');
          loadUserData(obj);
          loadDeviceData(obj);
        });
      }
    }
  }, true);

  if (userId === "0") {
    $scope.loggedUserAccount = true;
    loadMyData($scope.user);
    loadMyDevices(function(deviceLst){
      loadMySensors(deviceLst);
    });
  } else {
    $scope.loggedUserAccount = false;
    loadUserData($scope.user);
    loadUserDevices($scope.user.userId, function(deviceLst){
      loadUserSensors(deviceLst);
    });
  }

}]);

app.controller('NotificationCtrl', ['$rootScope', '$scope', '$http', 'ngDialog', function($rootScope, $scope, $http, ngDialog) {
  console.log("Notifications ctrller fired");

  $scope.devices = [];
  $scope.sensors = [];
  $scope.friendRequests = [];

  function loadFriendRequests(){
    $http.get('/api/friends/manage/').success(function(data) {
      $scope.friendRequests = data;
    });
  }

  $scope.acceptFriendRequest = function(id){
    $http({
      method: 'POST',
      url: '/api/friends/manage',
      data: id,
      headers: {
        'Content-Type': 'text/plain'
      }})
      .success(function(result) {
        _.remove($scope.friendRequests, function(request){
          return request.userId == id;
        });
        console.log($scope.friendRequests);
      });
  }

  $scope.deleteFriendRequest = function(id){
    $http.delete('/api/friends/manage', {data: id}).success(function(res){
      _.remove($scope.friendRequests, function(request){
        return request.userId == id;
      });
    });
  }

  function loadDeviceSubscriberName(lst) {
    console.log(lst);
    lst.forEach(function(device){
      device.subscriberIds.forEach(function(id){
        $http.get('/api/friends/' +id).success(function(data) {
          device.subscriber.push({name: data.firstName + ' ' + data.lastName, subscriberId: id});
        });
      });
    });
    lst.forEach(function(device){
      $http.get('/api/device/' +device.id).success(function(data) {
        device.name = data.name;
      });
    });
    $scope.devices = lst;
  }

  function loadDeviceNotifications(cb){
    var deviceLst = [];
    var devIdLst = [];

    $http.get('/api/device/subscriptions/manage').success(function(data) {
      devIdLst = _.keys(data);

      var dev = {
        id: '',
        name: '',
        subscriberIds: [],
        subscriber: []
      };

      devIdLst.forEach(function(id){
        dev.id = id;
        dev.subscriberIds = data[id];
        deviceLst.push(dev);
        console.log(data[id], data, id);
      });
      cb(deviceLst);
    });
  }

  function loadSensorSubscriberName(lst) {
    console.log(lst);
    lst.forEach(function(sensor){
      sensor.subscriberIds.forEach(function(id){
        $http.get('/api/friends/' +id).success(function(data) {
          sensor.subscriber.push({name: data.firstName + ' ' + data.lastName, subscriberId: id});
        });
      });
    });
    lst.forEach(function(sensor){
      $http.get('/api/sensor/' +sensor.id).success(function(data) {
        sensor.name = data.name;
      });
    });
    $scope.sensors = lst;
    $rootScope.dataLoaded = true;
  }

  function loadSensorNotifications(cb){
    var sensorLst = [];
    var senIdLst = [];

    $http.get('/api/sensor/subscriptions/manage').success(function(data) {
      senIdLst = _.keys(data);

      var sen = {
        id: '',
        name: '',
        subscriberIds: [],
        subscriber: []
      };

      senIdLst.forEach(function(id){
        sen.id = id;
        sen.subscriberIds = data[id];
        sensorLst.push(sen);
        console.log(data[id], data, id);
      });
      cb(sensorLst);
    });
  }

  $scope.acceptSubscription = function(subscriberId, sensorId, sub) {
    var postData = {
      "subscriberId": subscriberId,
      "sensorId": sensorId,
      "accept": true
    };

    $http.post('/api/sensor/subscriptions/manage', postData).success(function(data) {
      console.log(data, sub);
      sub.requestSent = true;
    });
  }

  $scope.rejectSubscription = function(subscriberId, sensorId, sub) {
    var postData = {
      "subscriberId": subscriberId,
      "sensorId": sensorId,
      "accept": false
    };

    $http.post('/api/sensor/subscriptions/manage', postData).success(function(data) {
      console.log(data);
      sub.requestSent = true;
    });
  }

  // Update notifications badge
  $scope.$watch('sensors', function(newval, old){
    $rootScope.notificationCount = newval.length;
  }, true);

  $scope.$watch('devices', function(newval, old){
    $rootScope.notificationCount += newval.length;
  }, true);
  $scope.$watch('friendRequests', function(newval, old){
    $rootScope.notificationCount += newval.length;
  }, true);

  // check new notifications every 5 seconds

  setInterval(function(){
    loadFriendRequests()
    loadSensorNotifications(loadSensorSubscriberName);
    loadDeviceNotifications(loadDeviceSubscriberName);
  }, 5000);


  // loadDevices(loadDeviceInfo);
  loadFriendRequests()
  loadSensorNotifications(loadSensorSubscriberName);
  loadDeviceNotifications(loadDeviceSubscriberName);
  $rootScope.dataLoaded = true;

}]);

app.directive('xngFocus', [function () {
  return function (scope, element, attrs) {
    return scope.$watch(attrs.xngFocus, function (newValue) {
      console.log(newValue);
      return newValue && element[0].focus();
    });
  };
}]);



//Dashboard ............................



app.controller('DashboardCtrl', ['$scope', '$http', '$rootScope', 'ngDialog', function($scope, $http, $rootScope, ngDialog) {
	console.log("DashboardCtrl ctrller fired");
	
	$scope.deviceIdList = [];
	$scope.deviceData = [];
	
	$scope.sensorIdList =[];
	$scope.sensorData = [];
		
	// Bar chart
	      
	function getDataForBar(chartData) {
		var barChartArray = [["Date"]];
		var timeArray = [];
		var msgArray = ["Date"];  
		
	    angular.forEach(chartData, function(record, key) {
	    	
	    	var dataItems = [];
	
			var date = new Date(record.dateTime);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
	
			var yearAndMonth = y+"-"+m+"-"+d;
	
			var index = timeArray.indexOf(yearAndMonth);
			var x = msgArray.indexOf(record.message);
	
	
			if(!isInt(record.message) && typeof(record.message)!='object')
			{
				if(index > -1)
	    		{            			
	    			if(x > -1)
	    			{
	    				var y = barChartArray[index+1][x] + 1;
	    				barChartArray[index+1][x] = y;
	    			}
	    			else
	    			{
	    				msgArray.push(record.message);
	    				var z = msgArray.indexOf(record.message);
	    				barChartArray[index+1][z] = 1;
	    			}
	    		}
	    		else
	    		{
	    			timeArray.push(yearAndMonth);
	
	    			if(x == -1)
	    			{
	    				msgArray.push(record.message);
	
	    			}
	    			
	    			
	    			dataItems[0] = new Date(yearAndMonth);
	    			for(var i = 1; i<msgArray.length;i++)
	    			{
	    				dataItems[i] = 0;
	    			}
	    			dataItems[msgArray.indexOf(record.message)] = 1;
	    			barChartArray.push(dataItems);
	    		}
				barChartArray[0] = msgArray;        			
			}	            	            
	    });	        
	    
	    var l = msgArray.length;
//	    console.log(l);
		for(var i=1; i<timeArray.length ; i++)
		{
			for(var k=1; k<l; k++)
			{
				if(barChartArray[i][k]== null){
					barChartArray[i][k]= 0 ;
				}
			}
			
		}
//		console.log(barChartArray);
		return barChartArray;	        
	};

	    
	//Geo chart
	
	function getDataForGeo(chartData) {
		var geoChartArray = [['Lat','Long']];
		angular.forEach(chartData, function(record, key) {
			
			if(typeof(record.message)=='object')
			{
				var lat = record.message.lat ;
				var lng = record.message.lng;
	
				var dataItem = [lat,lng];
				geoChartArray.push(dataItem);
			}
		});	    	
		return geoChartArray;
	};
	    
	// line chart
	    
	function getDataForLine(chartData){
		var lineChartArray = [['Time','Message']];
		
		angular.forEach(chartData, function(record, key) {
			var dataItems = [];
			
				  			
	    		var date = new Date(record.dateTime);
	    		
	    		if(isInt(record.message) && typeof(record.message)!='object'){
	    			dataItems[0] = new Date(date);
	    			dataItems[1] = record.message;
	    			
	    			lineChartArray.push(dataItems);
	    		}
			
		});
		return lineChartArray;
	};
	
	//Pie chart
	
	function getDataForPie(chartData){
		var pieChartArray = [];
		var deviceName = [];
		var msgCount = [];
		
		angular.forEach(chartData, function(record, key) {
			var index = deviceName.indexOf(record.name);
			
			if(index > -1){
				msgCount[index] = msgCount[index] + 1;
			}
			else{
				deviceName.push(record.name);
				msgCount.push(1);
			}
		});
		
		pieChartArray.push(["Device Name","Messages Count"]);
		
		for(var i=0; i<deviceName.length;i++){
			pieChartArray.push([deviceName[i],msgCount[i]]);
		}
		
		return pieChartArray;
	};
	    
	var data_geo = google.visualization.arrayToDataTable(getDataForGeo());

	  
	var options = {
	        title: 'Device Messages',
	        width: 800,
		    height: 450
	      };
	    
	var barChartFilter = new google.visualization.ControlWrapper({
		controlType: 'DateRangeFilter',
		options:{
		filterColumnLabel:'Date'
		
		}
	});
      	
	    
	var chart = {};
	chart.options = options;
	chart.filter = barChartFilter;
	    
	    $scope.deviceTypes = [
	                         { typeName: 'Device', typeValue: '1' },
	                         { typeName: 'Sensor', typeValue: '2' }
	                         ];
	    
	    
	    
	    $scope.selectDeviceType = function (type) {
        	$scope.chart.deviceType = type.typeValue;        	        	
    	}
	    
	    
	    $scope.barChart = function(){
	    	$scope.chart.type = "1";
	    }	    
	    $scope.lineChart = function(){
	    	$scope.chart.type = "2";
	    }
	    $scope.geoChart = function(){
	    	$scope.chart.type = "3";
	    }
	    $scope.pieChart = function(){
	    	$scope.chart.type = "4";
	    }
	    
	    	    
        chart.type = "1";
        chart.deviceType = "1";
        $scope.deviceType = $scope.deviceTypes[0];
        
        $scope.chart = chart;
        
        
    	function loadDevices(){
    		$http.get('/api/device').success(function(data) {
    		      data.forEach(function(device){

    		        dev = {
    		          id: '',
    		          name: '',
    		          userId: ''
    		        };

    		        dev.id = device.id;
    		        dev.name = device.name;
    		        dev.userId = device.parentUserId;
    		        $scope.deviceIdList.push(dev);
    		      });
    		});
    	}
    	
    	function loadSensors(){
			$http.get('/api/sensor').success(function(data){
				data.forEach(function(sensor){
					
					sen = {
							id:'',
							name: '',
							userId:''
					};
					sen.id = sensor.id;
					sen.name = sensor.name;
					sen.userId = sensor.parentUserId;
					$scope.sensorIdList.push(sen);
				});
				console.log(data);
			});
		}
    	
    	function loadDeviceData(obj){
//    		var userId = obj.userId;
    		
    		for(var i=0; i<$scope.deviceIdList.length;i++){
    			$http.get('/api/device/message/'+ obj.id).success(function(data){
    				data.forEach(function(device){
//    					if(userId == device.userId){
    						var dev = {
    								id: device.id,
    								message: device.message,
    								dateTime: device.dateTime,
    								deviceId: device.deviceId,
    								name: obj.name,
    								sensorId: device.sensorId
    						};
    						$scope.deviceData.push(dev);
//    					}
    				});
    			});
    			
    		}
    	}
    	
    	function loadSensorData(obj){
    		
    		for(var i=0; i<$scope.sensorIdList.length;i++){
    			$http.get('/api/sensor/message/'+ obj.id).success(function(data){
    				data.forEach(function(sensor){
    						var sen = {
    								id: sensor.id,
    								message: sensor.message,
    								dateTime: sensor.dateTime,
    								deviceId: sensor.deviceId,
    								name: obj.name,
    								sensorId: sensor.sensorId
    						};
    						$scope.sensorData.push(sen);
    				});
    				console.log(data);
    			});
    		}
    	}
    	
    	$scope.$watch('deviceIdList', function(newval, old){
    	    newval.forEach(function(obj){
    	      loadDeviceData(obj);
    	    });
//    	    console.log(newval);
    	  }, true);
    	
    	$scope.$watch('deviceData',function(newval,old){
    		console.log(newval);
    		chart.deviceBarChart = google.visualization.arrayToDataTable(getDataForBar(newval));
    		chart.deviceLineChart = google.visualization.arrayToDataTable(getDataForLine(newval));
    		chart.devicePieChart = google.visualization.arrayToDataTable(getDataForPie(newval));
    		chart.deviceGeoChart = google.visualization.arrayToDataTable(getDataForGeo(newval));
    	},true);
    	
    	$scope.$watch('sensorIdList', function(newval, old){
    	    newval.forEach(function(obj){
    	    	loadSensorData(obj);
    	    });
//    	    console.log(newval);
    	  }, true);
    	
    	$scope.$watch('sensorData',function(newval,old){
//    		console.log(newval);
    		chart.SensorBarChart = google.visualization.arrayToDataTable(getDataForBar(newval));
    		chart.SensorLineChart = google.visualization.arrayToDataTable(getDataForLine(newval));
    		chart.SensorPieChart = google.visualization.arrayToDataTable(getDataForPie(newval));
    		chart.SensorGeoChart = google.visualization.arrayToDataTable(getDataForGeo(newval));
    	},true);
    	
    	loadDevices();
    	loadSensors();
        
        
	    function isInt(value) {
			  return !isNaN(value) && parseInt(Number(value)) == value && !isNaN(parseInt(value, 10));
		} 

}]);

app.directive('gChart', function() {
    
	
	return {
	      restrict: 'A',
	      link: function ($scope, elm, attrs) {
	        $scope.$watch('chart', function () {
			var type = $scope.chart.type;
			var deviceType = $scope.chart.deviceType;
			var chart = " ";
			if(type=="1" && deviceType=="1"){
				chart = new google.visualization.ColumnChart(elm[0]);
				chart.draw($scope.chart.deviceBarChart, $scope.chart.options);
			}
			else if(type=="1" && deviceType=="2"){
				chart = new google.visualization.ColumnChart(elm[0]);
				chart.draw($scope.chart.SensorBarChart, $scope.chart.options);
			}
			else if(type=="2" && deviceType=="1"){
				chart = new google.visualization.LineChart(elm[0]);
				chart.draw($scope.chart.deviceLineChart, $scope.chart.options);
			}
			else if(type=="2" && deviceType=="2"){
				chart = new google.visualization.LineChart(elm[0]);
				chart.draw($scope.chart.SensorLineChart, $scope.chart.options);
			}
			else if(type=="3" && deviceType=="1"){
				chart = new google.visualization.GeoChart(elm[0]);
				chart.draw($scope.chart.deviceGeoChart, $scope.chart.options);
			}
			else if(type=="3" && deviceType=="2"){
				chart = new google.visualization.GeoChart(elm[0]);
				chart.draw($scope.chart.SensorGeoChart, $scope.chart.options);
			}
			else if(type=="4" && deviceType=="1"){
				chart = new google.visualization.PieChart(elm[0]);
				chart.draw($scope.chart.devicePieChart, $scope.chart.options);
			}
			else if(type=="4" && deviceType=="2"){
				chart = new google.visualization.PieChart(elm[0]);
				chart.draw($scope.chart.SensorPieChart, $scope.chart.options);
			}
	        	
	        	
			},true);
	      }
	    }
    
});


