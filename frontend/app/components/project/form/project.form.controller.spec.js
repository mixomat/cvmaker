(function () {
  'use strict';

  describe('Controller: projectForm', function () {
    var ctrl, scope, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($rootScope, $controller, _$httpBackend_, _$log_, Project) {
      httpBackend = _$httpBackend_;
      scope = $rootScope.$new();
      var ctrlBinding = {
        project: new Project(),
        onUpdate: function () {
        },
        onCancel: function () {
        }
      };
      ctrl = $controller('ProjectFormController', {$scope: scope, $log: _$log_}, ctrlBinding);
    }));


    it('should have a project property', function () {
      expect(ctrl.project).toBeDefined();
    });

    it('should have a onUpdate function', function () {
      expect(ctrl.onUpdate).toBeDefined();
    });

    it('should have a onCancel function', function () {
      expect(ctrl.onCancel).toBeDefined();
    });

    it('should define a saveProject function', function () {
      expect(ctrl.saveProject).toBeDefined();
    });

    it('should post a project on saving project', function () {
      ctrl.saveProject();

      httpBackend.expect('POST', '/api/projects', ctrl.project).respond('201', '');
      httpBackend.flush();
    });

    it('should put a project when saving existing project', function () {
      ctrl.project._links = {self: {href: 'http://localhost:8080/api/projects/123abc'}};
      ctrl.saveProject();

      httpBackend.expect('PUT', '/api/projects/123abc', ctrl.project).respond('200', '');
      httpBackend.flush();
    });

  });
})();
