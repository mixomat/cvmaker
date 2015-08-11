/* global _:false */
(function () {
  'use strict';

  describe('Controller: main', function () {

    beforeEach(module('cvmaker'));

    var main, httpBackend;

    beforeEach(inject(function ($controller, _$httpBackend_) {
      main = $controller('MainController');
      httpBackend = _$httpBackend_;

      httpBackend.when('GET', '/projects').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      });

      httpBackend.flush();
    }));

    it('should define a creationDate', function () {
      expect(main.creationDate).toBeCloseTo(1438285936992);
    });

    it('should define projects', function () {
      expect(main.projects).toBeDefined();
    });

    it('should define a project with id 1', function () {
      expect(_.first(main.projects).id).toBeDefined();
    });

    it('should define a editProject function', function () {
      expect(main.editProject).toBeDefined();
    });

  });
})();
