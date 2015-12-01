(function () {

  "use strict"

  var module = angular.module('devBookmarksClientApp.login');

  module.service('authenticationService', ["$http", "$cookies", "$cookieStore","$state",
    function ($http, $cookies, $cookieStore, $state) {

      var self = this;
      self.user = {};
      self.login = login;
      self.logout = logout;
      self.isLoggedIn = isLoggedIn;
      self.credentials = {username: "", password: ""};

      init();

      return self;

      function init() {
        // $cookie doesn't work
        var user = $cookieStore.get("user");
        if (typeof user === "undefined")
            return;
        self.user = user;
      }

      function isLoggedIn() {
        return typeof self.user.name !== "undefined" && self.user.name !== "";
      }

      function login() {
        var headers = self.credentials ? {
          authorization: "Basic " + btoa(self.credentials.username + ":" + self.credentials.password)
        } : {};

        $http.get('/api/user', {headers: headers}).success(function (data) {
          if (data.name) {
            self.user = data;
            $cookieStore.put("user", self.user);
            $state.go("main");
          }
        }).error(function () {
        });
      }

      function logout() {
        return $http.post('/api/logout').then(function () {
          $cookieStore.remove("user");
          self.user = {};
          $state.go("login");
        });
      }
    }]);
})();
