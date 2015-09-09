(function () {
  'use strict';

  describe('Controller: login', function () {
    var loginController, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($controller, _$httpBackend_) {
      loginController = $controller('LoginController');
      httpBackend = _$httpBackend_;

      //httpBackend.flush();
    }));

    it('should define a authenticate function', function () {
      expect(loginController.authenticate).toBeDefined();
    });
  });
})();
