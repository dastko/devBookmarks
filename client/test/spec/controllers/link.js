'use strict';

describe('Controller: LinkCtrl', function () {

  // load the controller's module
  beforeEach(module('devBookmarksClientApp'));

  var LinkCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LinkCtrl = $controller('LinkCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(LinkCtrl.awesomeThings.length).toBe(3);
  });
});
