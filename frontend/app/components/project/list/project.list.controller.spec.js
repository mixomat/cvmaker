(function () {
  'use strict';

  describe('Controller: projectList', function () {
    var projectList, httpBackend;

    beforeEach(module('cvmaker'));
    beforeEach(inject(function ($controller, _$httpBackend_) {
      projectList = $controller('ProjectListController');
      httpBackend = _$httpBackend_;

    }));

    it('should define a deleteProject function', function () {
      expect(projectList.deleteProject).toBeDefined()
    });
  });
})();
