angular.module('Authentication')
 
.controller('LoginController',
    ['$scope', '$rootScope', '$location', 'AuthenticationService',
    function ($scope, $rootScope, $location, AuthenticationService) {
        // reset login status
        AuthenticationService.ClearCredentials();
 
        $scope.submitLoginForm = function () {
            $scope.dataLoading = true;
            AuthenticationService.Login($scope.user.username, $scope.user.password, function(response) {
            	if(response.success) {
                    AuthenticationService.SetCredentials($scope.user.username, $scope.user.password);
                    var data = $scope.user;                    
                    $location.path('/home');
                } else {
                    $scope.error = response.message;
                    $scope.dataLoading = false;                   
                }
            });
        };
        
        $scope.submitSignupForm = function() {
            var data = $scope.fields;
            $http.post("/api/signup", data);
            console.log(data);
          };
      
    }]);