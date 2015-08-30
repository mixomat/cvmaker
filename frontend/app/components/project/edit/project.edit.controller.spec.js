(function () {
  'use strict';

  describe('Controller: projectEdit', function () {
    var projectEdit, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($controller, _$httpBackend_, Project) {
      projectEdit = $controller('ProjectEditController', {project: new Project()});
      httpBackend = _$httpBackend_;

      //httpBackend.flush();
    }));

    it('should define a project property', function () {
      expect(projectEdit.project).toBeDefined();
    });

    it('should define a title property', function () {
      expect(projectEdit.title).toBeDefined();
    });

    it('should define the correct title value', function () {
      expect(projectEdit.title).toBe('Create Project');
    });

  });
})();
