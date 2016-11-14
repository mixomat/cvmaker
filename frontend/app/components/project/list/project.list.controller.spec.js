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
      spyOn(hotkeys, 'add').and.returnValue(hotkeys);
    }

    function httpBackendBehavior() {
      httpBackend.when('GET', '/api/projects?sort=start,desc').respond({
        _embedded: {
          projects: [{id: 1}, {id: 2}]
        }
      });
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

    it('should define a scrollToProject function', function () {
      expect(projectList.selectNextProject).toBeDefined();
    });

    it('should define a scrollToProject function', function () {
      expect(projectList.selectPreviousProject).toBeDefined();
    });

    it('should define a scrollToProject function', function () {
      expect(projectList.isSelectedProject).toBeDefined();
    });

    it('should set the selectedProject to -1 on selectPreviousProject', function () {
      projectList.selectPreviousProject();
      expect(projectList.isSelectedProject(-1)).toBeTruthy();
    });

    it('should set the selectedProject to -1 on two invocations of selectPreviousProject', function () {
      projectList.selectPreviousProject();
      projectList.selectPreviousProject();
      expect(projectList.isSelectedProject(-1)).toBeTruthy();
    });

    it('should set the selectedProject to 0 on selectNextProject', function () {
      projectList.selectNextProject();
      expect(projectList.isSelectedProject(0)).toBeTruthy();
    });

    it('should set the selectedProject to 1 on two invocations of selectNextProject', function () {
      projectList.selectNextProject();
      projectList.selectNextProject();
      expect(projectList.isSelectedProject(1)).toBeTruthy();
    });

    it('should set the selectedProject to 1 on three invocations of selectNextProject', function () {
      projectList.selectNextProject();
      projectList.selectNextProject();
      projectList.selectNextProject();
      expect(projectList.isSelectedProject(1)).toBeTruthy();
    });

    it('should configure the hotkey "c" for create project', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({combo: 'c'}));
    });

    it('should configure the hotkey "e" for edit project', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({combo: 'e'}));
    });

    it('should configure the hotkey "j" for select next project', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({combo: 'j'}));
    });

    it('should configure the hotkey "k" for select previous project', function () {
      expect(hotkeys.add).toHaveBeenCalledWith(jasmine.objectContaining({combo: 'k'}));
    });
  });
})();
