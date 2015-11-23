'use strict';

/* Controllers */

  // bootstrap controller
  app.controller('UsersCtrl', ['$scope', '$mdDialog', function($scope, $mdDialog) {
    var originatorEv;
    $scope.openMenu = function($mdOpenMenu, ev) {
      originatorEv = ev;
      $mdOpenMenu(ev);
    };

  }]); 