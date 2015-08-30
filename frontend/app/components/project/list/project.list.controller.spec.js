/* global _:false */
(function () {
  'use strict';

  describe('Controller: projectList', function () {
    var projectList, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($controller, _$httpBackend_) {
      projectList = $controller('ProjectListController');
      httpBackend = _$httpBackend_;

      httpBackend.when('GET', '/api/projects?sort=start,desc').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      });

      httpBackend.flush();
    }));

    it('should load a list of projects', function () {
      expect(projectList.projects).toBeDefined();
    });

    it('should define a project with id 1', function () {
      expect(_.first(projectList.projects).id).toBe(1);
    });

    it('should define a deleteProject function', function () {
      expect(projectList.deleteProject).toBeDefined();
    });
  });
})();
