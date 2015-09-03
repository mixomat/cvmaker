/* global _:false, spyOn:false */
(function () {
  'use strict';

  describe('Controller: projectList', function () {
    var hotkeys, projectList, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($controller, $rootScope, _$httpBackend_, _hotkeys_) {
      hotkeys = _hotkeys_;
      httpBackend = _$httpBackend_;

      spyBehavior();
      projectList = $controller('ProjectListController', {$scope: $rootScope.$new()});

      httpBackendBehavior();
    }));

    function spyBehavior() {
      spyOn(hotkeys, 'bindTo').and.returnValue(hotkeys);
      spyOn(hotkeys, 'add');
    }

    function httpBackendBehavior() {
      httpBackend.when('GET', '/api/projects?sort=start,desc').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      });
      httpBackend.expect('GET', 'app/components/project/list/project.list.html').respond(200);
      httpBackend.flush();
    }

    it('should load a list of projects', function () {
      expect(projectList.projects).toBeDefined();
    });

    it('should define a project with id 1', function () {
      expect(_.first(projectList.projects).id).toBe(1);
    });

    it('should define a deleteProject function', function () {
      expect(projectList.deleteProject).toBeDefined();
    });

    it('should configure the hotkey "c" for create project', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({combo: 'c'}));
    });

  });
})();
