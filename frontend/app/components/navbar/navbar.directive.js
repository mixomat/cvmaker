(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('cvNavbar', cvNavbar);

  /** @ngInject */
  function cvNavbar() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      scope: {},
      bindToController: {
        title: '@'
      },
      controller: function () {
      },
      controllerAs: 'nav'
    };
  }

})();
