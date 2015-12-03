(function () {
  "use strict";

  var module = angular.module('devBookmarksClientApp.link');

  module.service('linkService', ["$http", "$state", "usSpinnerService", function ($http) {

    var self = this;
    self.addLink = addLink;
    self.addFriend = addFriend;
    self.loadTags = loadTags;
    self.getAllLinks = getAllLinks;
    self.loadNext = loadNext;
    self.loadPrevious = loadPrevious;
    self.hasNext = false;
    self.hasPrevious = false;
    self.page = 1;
    self.tags = [];
    self.links = [];
    loadData();
    return self;

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

    function getAllLinks() {
      $http.get("/api/pagination/" + 1).then(function (response) {
        self.links.push((response.data.linkList));
      }, function (response) {
        console.log(response)
      })
    }

    function loadNext() {
      self.page++;
      loadData();
    }


    function loadPrevious() {
      self.page--;
      loadData();
    }

    function loadData() {
      $http.get("/api/pagination/" + self.page).then(function (response) {
        console.log(response);
        self.links = response.data.linkList;
        self.hasNext = response.hasNext;
        self.hasPrevious = response.hasPrevious;
      }, function (response) {
        console.log(response)
      });
    }

    function loadTags(query) {
      var promise = $http.get("/api/tags/" + query)
        .then(function (response) {
          return response.data;
        });
      return promise;
    }
  }]);
})();
