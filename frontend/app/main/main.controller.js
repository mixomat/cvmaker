(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($timeout, toastr, Project) {
    var vm = this;

    vm.classAnimation = '';
    vm.creationDate = 1438285936992;
    vm.showToastr = showToastr;
    vm.projects = Project.all();

    activate();

    function activate() {
      $timeout(function () {
        vm.classAnimation = 'rubberBand';
      }, 4000);
    }

    function showToastr() {
      toastr.info('Hello');
      vm.classAnimation = '';
    }
  }
})();
