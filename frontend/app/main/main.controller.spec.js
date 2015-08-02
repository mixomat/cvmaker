(function () {
  'use strict';

  describe('Controller: main', function () {

    beforeEach(module('cvmaker'));

    var main, httpBackend;

    beforeEach(inject(function ($controller, _$httpBackend_) {
      main = $controller('MainController');
      httpBackend = _$httpBackend_;

      httpBackend.when('GET', '/reservations').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      })
      ;
    }));

    it('should define a creationDate', function () {
      expect(main.creationDate).toBeCloseTo(1438285936992);
    });

    it('should define projects', function () {
      expect(main.projects).toBeDefined();
    });

  });
})();
