(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectEditController', ProjectEditController);

  /** @ngInject */
  function ProjectEditController($log, project) {
    $log.info('initialized with project: ', project);

    var vm = this;
    vm.project = project;
    vm.title = vm.project.id ? 'Edit Project' : 'Create Project';
  }

})();
