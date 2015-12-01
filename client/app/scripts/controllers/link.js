(function () {
  "use strict";

  var mod = angular.module("devBookmarksClientApp.link",
    []);

  mod.config(function config($stateProvider) {
    $stateProvider.state('link', {
      url: '/link',
      resolve: {
        '': function (authenticationService, linkService, $state) {
          if (!authenticationService.isLoggedIn()) {
            linkService.getAllLinks();
            authenticationService.init();
          } else {
            $state.go('login');
          }
          //authenticationService.init();
        }
      },
      templateUrl: 'views/link.html',
      controller: 'LinkController',
      controllerAs: 'lCtrl',
      data: {pageTitle: 'What is It?'}
    });
  });

  mod.controller("LinkController", function (linkService) {
    var self = this;
    self.newLink = {name: "", details: "", tags: []};
    self.addLink = addLink;
    self.linkService = linkService;

    function addLink() {
      self.linkService.addLink(self.newLink);
      self.newLink = {name: "", details: "", tags: []};
    }
  });
})();
