/* global _:false */
(function () {
  'use strict';

  describe('Controller: project', function () {

    beforeEach(module('cvmaker'));

    var project, httpBackend;

    beforeEach(inject(function ($controller, _$httpBackend_) {
      project = $controller('ProjectController');
      httpBackend = _$httpBackend_;

      httpBackend.when('GET', '/api/projects?sort=start,desc').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      });

      httpBackend.flush();
    }));

    it('should define projects', function () {
      expect(project.projects).toBeDefined();
    });

    it('should define a project with id 1', function () {
      expect(_.first(project.projects).id).toBe(1);
    });

    it('should define a editing property', function () {
      expect(project.editing).toBeFalsy();
    });

    it('should define a newProject function', function () {
      expect(project.newProject).toBeDefined();
    });

    it('should set editing to true, when newProject is executed', function () {
      project.newProject();

      expect(project.editing).toBeTruthy();
    });

    it('should define a project property when newProject is executed', function () {
      project.newProject();

      expect(project.project).toBeDefined();
    });

    it('should define a editProject function', function () {
      expect(project.editProject).toBeDefined();
    });

    it('should define a onUpdate function', function () {
      expect(project.onUpdate).toBeDefined();
    });

    it('should define a onCancel function', function () {
      expect(project.onCancel).toBeDefined();
    });
  });
})();
