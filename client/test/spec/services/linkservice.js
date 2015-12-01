'use strict';

describe('Service: linkService', function () {

  // load the service's module
  beforeEach(module('devBookmarksClientApp'));

  // instantiate service
  var linkService;
  beforeEach(inject(function (_linkService_) {
    linkService = _linkService_;
  }));

  it('should do something', function () {
    expect(!!linkService).toBe(true);
  });

});
