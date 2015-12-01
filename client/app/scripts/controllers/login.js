(function () {
  "use strict";

  var mod = angular.module("devBookmarksClientApp.login",
    []);

  mod.config(function config($stateProvider) {
    $stateProvider.state('login', {
      url: '/login',
      templateUrl: 'views/login.html',
      controller: 'LoginController',
      controllerAs: 'LoginCtrl',
      data: {pageTitle: 'What is It?'}
    });
  });

  mod.controller("LoginController", ["authenticationService", function(authenticationService){
    var self = this;
    self.authService = authenticationService;
    self.viewLoading = true;
  }]);
})();
