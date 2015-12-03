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
          }
        }
      },
      templateUrl: 'views/link.html',
      controller: 'LinkController',
      controllerAs: 'lCtrl',
      data: {pageTitle: 'What is It?'}
    });
  });

  mod.controller("LinkController", function ($scope, linkService, $uibModal) {
    var self = this;
    self.newLink = {name: "", details: "", tags: []};
    self.open = open;
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

    function open(link) {
      $uibModal.open({
        templateUrl: 'views/modal.html',
        controller: 'ModalController',
        resolve: {
          link: function () {
            return self.linkService.links[link];
          }
        }
      });
    };
  });

  mod.controller("ModalController", function ($scope, $uibModalInstance, link) {

    $scope.link = link;

    $scope.ok = function () {
      $uibModalInstance.close();
    };

    $scope.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };
  });
  })();
