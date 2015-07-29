var app = angular.module('CobWebApp', ['ngMaterial']);



//Config theme
app.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('blue')
        .primaryPalette('blue')
        .accentPalette('red');
});