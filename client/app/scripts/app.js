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

  app.config(function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/main");

    $stateProvider.
      state('main', {
        url: '/main',
        templateUrl: 'views/main.html',
        controller: 'MainController'
      });
  });
})();
