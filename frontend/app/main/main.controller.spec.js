/* global _:false */
(function () {
  'use strict';

  describe('Controller: main', function () {

    beforeEach(module('cvmaker'));

    var main, httpBackend;

    beforeEach(inject(function ($controller, _$httpBackend_) {
      main = $controller('MainController');
      httpBackend = _$httpBackend_;

      httpBackend.when('GET', '/projects?sort=start,desc').respond({
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

    it('should define a editing property', function () {
      expect(main.editing).toBeFalsy();
    });

    it('should define a newProject function', function () {
      expect(main.newProject).toBeDefined();
    });

    it('should set editing to true, when newProject is executed', function () {
      main.newProject();

      expect(main.editing).toBeTruthy();
    });

    it('should define a project property when newProject is executed', function () {
      main.newProject();

      expect(main.project).toBeDefined();
    });

    it('should define a editProject function', function () {
      expect(main.editProject).toBeDefined();
    });

    it('should define a onSave function', function () {
      expect(main.onSave).toBeDefined();
    });

    it('should define a onCancel function', function () {
      expect(main.onCancel).toBeDefined();
    });
  });
})();
