(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('cvNavbar', cvNavbar);

  /** @ngInject */
  function cvNavbar() {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      scope: {},
      bindToController: {
        title: '@'
      },
      controller: function () {
        var vm = this;
        vm.pageTitle = vm.title;
      },
      controllerAs: 'vm'
    };

    return directive;
  }

})();
