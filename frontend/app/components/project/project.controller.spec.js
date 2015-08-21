(function () {
  'use strict';

  describe('Controller: project', function () {
    var project, scope, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($rootScope, $controller, _$httpBackend_, Project) {
      httpBackend = _$httpBackend_;
      scope = $rootScope.$new();
      var ctrlBinding = {
        project: new Project(),
        onSave: function () {
        },
        onCancel: function () {
        }
      };
      project = $controller('ProjectController', {$scope: scope}, ctrlBinding);

    }));


    it('should have a project property', function () {
      expect(project.onSave).toBeDefined();
    });

    it('should have a onSave function', function () {
      expect(project.onSave).toBeDefined();
    });

    it('should have a onCancel function', function () {
      expect(project.onCancel).toBeDefined();
    });

    it('should define a saveProject function', function () {
      expect(project.saveProject).toBeDefined();
    });

    it('should post a project on saveProject', function () {
      project.saveProject();

      httpBackend.expect('POST', '/projects', project.project).respond('201', '');
      httpBackend.flush();
    });

  });
})();
