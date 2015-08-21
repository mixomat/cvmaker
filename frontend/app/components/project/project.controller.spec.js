(function () {
  'use strict';

  describe('Controller: project', function () {
    var ctrl, scope, httpBackend;

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
      ctrl = $controller('ProjectController', {$scope: scope}, ctrlBinding);
    }));


    it('should have a project property', function () {
      expect(ctrl.project).toBeDefined();
    });

    it('should have a onSave function', function () {
      expect(ctrl.onSave).toBeDefined();
    });

    it('should have a onCancel function', function () {
      expect(ctrl.onCancel).toBeDefined();
    });

    it('should define a saveProject function', function () {
      expect(ctrl.saveProject).toBeDefined();
    });

    it('should post a project on saving project', function () {
      ctrl.saveProject();

      httpBackend.expect('POST', '/projects', ctrl.project).respond('201', '');
      httpBackend.flush();
    });

    it('should put a project when saving existing project', function () {
      ctrl.project.id = 1;
      ctrl.saveProject();

      httpBackend.expect('PUT', '/projects/1', ctrl.project).respond('200', '');
      httpBackend.flush();
    });

  });
})();
