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

    it('should define projects', function () {
      expect(main.projects).toBeDefined();
    });

    it('should define a project with id 1', function () {
      expect(_.first(main.projects).id).toBe(1);
    });

    it('should define a isCreating property', function () {
      expect(main.isCreating).toBeFalsy();
    });

    it('should define a newProject function', function () {
      expect(main.newProject).toBeDefined();
    });

    it('should set isCreating to true, when newProject is executed', function () {
      main.newProject();

      expect(main.isCreating).toBeTruthy();
    });

    it('should define a project property when newProject is executed', function () {
      main.newProject();

      expect(main.project).toBeDefined();
    });

    it('should define a editProject function', function () {
      expect(main.editProject).toBeDefined();
    });

    it('should define a saveProject function', function () {
      expect(main.saveProject).toBeDefined();
    });

    it('should define a cancel function', function () {
      expect(main.cancel).toBeDefined();
    });
  });
})();
