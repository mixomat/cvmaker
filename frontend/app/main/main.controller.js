(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($log, toastr, Project) {
    var vm = this;

    vm.creationDate = 1438285936992;
    vm.projects = Project.all();
    vm.showToastr = showToastr;
    vm.editProject = editProject;

    function showToastr() {
      toastr.info('Hello');
    }

    function editProject(project) {
      $log.debug('editing project: ' + project);
    }
  }
})();
