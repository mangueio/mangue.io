'use strict';

/* Controllers */

  app.controller('AppsCtrl', ['$scope', '$state', '$http', 'MANGUE', function($scope, $state, $http, MANGUE){
    /* get current session user or set to null if there's no user in session */
    $scope.app.user = MANGUE_USER && MANGUE_USER.id ? MANGUE_USER : null;
    var searchAppsConfig = {
      'params': {'userId':$scope.app.user.id}
    }
    $http.get(MANGUE.baseUrl + '/data/apps/search/findByUserIds', searchAppsConfig).success(function(result){
      $scope.apps = result.apps;
    });

  }])

