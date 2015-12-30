///* global spyOn:false */
(function () {
  'use strict';

  describe('Controller: projectForm', function () {
    var ctrl, httpBackend, hotkeys;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($rootScope, $controller, _$httpBackend_, _hotkeys_, _$log_, Project) {
      httpBackend = _$httpBackend_;
      hotkeys = _hotkeys_;

      spyOn(hotkeys, 'bindTo').and.returnValue(hotkeys);
      spyOn(hotkeys, 'add').and.returnValue(hotkeys);

      ctrl = $controller('ProjectFormController', {$scope: $rootScope.$new()}, {project: new Project()});
    }));

    it('should set a "esc" hotkey binding for cancel', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({
        combo: 'esc',
        allowIn: ['INPUT', 'SELECT', 'TEXTAREA']
      }));
    });

    it('should have a project property', function () {
      expect(ctrl.project).toBeDefined();
    });

    it('should define a saveProject function', function () {
      expect(ctrl.saveProject).toBeDefined();
    });

    it('should post a project on saving project', function () {
      ctrl.saveProject();

      httpBackend.expect('POST', '/api/projects', ctrl.project).respond(201);
      httpBackend.flush();
    });

    it('should put a project when saving existing project', function () {
      ctrl.project._links = {self: {href: 'http://localhost:8080/api/projects/123abc'}};
      ctrl.saveProject();

      httpBackend.expect('PUT', '/api/projects/123abc', ctrl.project).respond(200);
      httpBackend.flush();
    });

  });
})();
