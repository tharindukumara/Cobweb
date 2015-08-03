var app = angular.module('CobWebApp', []);

app.controller('LoginCtrl', ['$scope', '$http', function($scope, $http) {
  console.log("login ctrl fired");

  $scope.signup = function(user) {

    var userData = {
      firstname: user.firstname,
      lastname: user.lastname,
      email: user.email,
      password: user.password
    };

    if ($scope.signupForm.$valid) {
      $http.post('http://localhost:8080/cobweb/anon/signup', userData).
      then(function(response) {
        console.log(response);
      });
    }
  }
}]);

app.directive('compareTo', [function () {
  return {
    require: "ngModel",
    scope: {
        otherModelValue: "=compareTo"
    },
    link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
            return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
            ngModel.$validate();
        });
    }
  };
}]);

