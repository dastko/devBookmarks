(function () {
  "use strict";

  var module = angular.module('devBookmarksClientApp.link');

  module.service('linkService', ["$http", "$state", "usSpinnerService", function ($http) {

    var self = this;
    self.addLink = addLink;
    self.addFriend = addFriend;
    self.loadTags = loadTags;
    self.getAllLinks = getAllLinks;
    self.tags = [];
    self.links = [];

    function addLink(newLink) {
      $http.post("/api/saveLink", newLink)
        .then(function (response) {
          self.tags.push(response.data);
        }, function (response) {
          console.log(response);
        });
    }

    function addFriend(newFriend) {
      $http.post("/api/addFriend", newFriend)
        .then(function (response) {
          console.log(response);
        }, function (response) {
          console.log(response);
        });
    }


    function getAllLinks()
    {
      $http.get("/api/user/links").then(function (response){
        console.log(response.data);
        self.links.push((response.data));
      }, function (response) {
        console.log(response)
      })
    }

    function loadTags(query)
    {
      var promise = $http.get("/api/tags/" + query)
        .then(function (response){
          return response.data;
        });
      return promise;
    }
    return self;
  }]);
})();
