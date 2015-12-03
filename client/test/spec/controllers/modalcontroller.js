'use strict';

describe('Controller: ModalcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('devBookmarksClientApp'));

  var ModalcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ModalcontrollerCtrl = $controller('ModalcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ModalcontrollerCtrl.awesomeThings.length).toBe(3);
  });
});
