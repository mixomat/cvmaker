/* global _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectListController', ProjectListController);

  /** @ngInject */
  function ProjectListController($log, toastr, Project) {
    var vm = this;
    vm.deleteProject = deleteProject;

    loadProjects();

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ', vm.projects);
      });
    }

    function deleteProject(project) {
      $log.debug('deleting project: ', project);
      Project.delete({projectId: project.id}).$promise.then(function () {
        toastr.info('Project "' + project.title + '" deleted');
        loadProjects();
      });
    }

  }

})();
