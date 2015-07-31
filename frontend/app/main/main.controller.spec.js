(function () {
  'use strict';

  describe('controllers', function () {

    beforeEach(module('cvmaker'));

    it('should define more than 5 awesome things', inject(function ($controller) {
      var main = $controller('MainController');

      expect(main.creationDate).toBeCloseTo(1438285936992);
    }));
  });
})();
