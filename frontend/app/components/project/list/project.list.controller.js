/* global _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectListController', ProjectListController);

  /** @ngInject */
  function ProjectListController($scope, $log, toastr, Project, $state, hotkeys) {
    var vm = this;
    vm.deleteProject = deleteProject;

    configureHotKeys();
    loadProjects();

    function configureHotKeys() {
      hotkeys.bindTo($scope).add({
        combo: 'c',
        description: 'Create a new project',
        callback: function () {
          $state.go('projectEdit');
        }
      });
    }

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ', vm.projects);
      });
    }

    function deleteProject(project) {
      $log.debug('deleting project: ', project);
      Project.delete({projectId: project.id}).$promise.then(deleted);
    }

    function deleted() {
      toastr.info('Project deleted');
      loadProjects();
    }
  }

})();
