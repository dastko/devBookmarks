(function () {
  "use strict";

  var app = angular
    .module('devBookmarksClientApp', [
      'ngAnimate',
      'ngCookies',
      'ngResource',
      'ngRoute',
      'ngSanitize',
      'ngTouch',
      "ui.router",
      "ngTagsInput",
      "angularSpinner",
      'devBookmarksClientApp.link',
      'devBookmarksClientApp.login'
    ]);

  app.factory('httpInterceptor', function ($q, $rootScope){
    var numLoadings = 0;

    return {
      request: function (config) {

        numLoadings++;

        // Show loader
        $rootScope.$broadcast("loader_show");
        return config || $q.when(config)

      },
      response: function (response) {

        if ((--numLoadings) === 0) {
          // Hide loader
          $rootScope.$broadcast("loader_hide");
        }

        return response || $q.when(response);

      },
      responseError: function (response) {

        if (!(--numLoadings)) {
          // Hide loader
          $rootScope.$broadcast("loader_hide");
        }

        return $q.reject(response);
      }
    };
  });

  app.directive("loader", function ($rootScope) {
      return function ($scope, element, attrs) {
        $scope.$on("loader_show", function () {
          return element.show();
        });
        return $scope.$on("loader_hide", function () {
          return element.hide();
        });
      };
    }
  );

  app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
  });

  app.config(function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/main");

    $stateProvider.
      state('main', {
        url: '/main',
        templateUrl: 'views/main.html',
        controller: 'MainController'
      });
  });

  app.run(function ($state, authenticationService){

    if(authenticationService.isLoggedIn === false){
      $state.go('login');
    }
  });
})();
