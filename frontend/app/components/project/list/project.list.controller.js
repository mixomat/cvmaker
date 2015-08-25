/* global toastr: false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectListController', ProjectListController);

  /** @ngInject */
  function ProjectListController($log, toastr, Project) {
    var vm = this;
    vm.deleteProject = deleteProject;

    function deleteProject(project) {
      $log.debug('deleting project: ', project);
      Project.delete({projectId: project.id}).$promise.then(function () {
        toastr.info('Project "' + project.title + '" deleted');
        vm.onUpdate();
      });
    }

  }

})();
