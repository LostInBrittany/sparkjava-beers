'use strict';

/* Controllers */

angular
  .module('BeerControllers', [])
  .controller('BeerListCtrl', ['$scope', '$http', function($scope, $http) {

    $http.get('BeerList').success(function(data) {
      $scope.beers = data;
    });

    $scope.orderProp = 'alcohol';
  }])
  .controller('BeerDetailCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
    $http.get('Beer/' + $routeParams.beerId).success(function(data) {
      $scope.beer = data;      
      $scope.mainImg = $scope.beer.img;

      $scope.setImage = function(img) {
        $scope.mainImg = img;
      }
    });
  }]);

