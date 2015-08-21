(function () {
  'use strict';

  describe('Resource: project', function () {

    beforeEach(module('cvmaker'));

    var Project;

    beforeEach(inject(function (_Project_) {
      Project = _Project_;
    }));

    it('should be defined', function () {
      expect(Project).toBeDefined();
    });

    it('should expose an all function', function () {
      expect(Project.all).toBeDefined();
    });

    it('should expose an update function', function () {
      expect(Project.update).toBeDefined();
    });

  });


})();
