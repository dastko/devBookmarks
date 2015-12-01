(function () {
  "use strict";

  var mod = angular.module("devBookmarksClientApp.link",
    []);

  mod.config(function config($stateProvider) {
    $stateProvider.state('link', {
      url: '/link',
      resolve: {
        'onStart': function ($state, authenticationService, linkService) {
          if (!authenticationService.isLoggedIn()) {
            console.log(authenticationService.isLoggedIn());
            $state.go("login");
          } else {
            linkService.getAllLinks();
          }
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
    self.newFriend = {requesterId: "", accepterId: ""};
    self.addLink = addLink;
    self.addFriend = addFriend;
    self.linkService = linkService;

    function addLink() {
      self.linkService.addLink(self.newLink);
      self.newLink = {name: "", details: "", tags: []};
    }

    function addFriend()
    {
      self.linkService.addFriend(self.newFriend);
      self.newFriend = {requesterId: "", accepterId: ""};
    }
  });
})();
