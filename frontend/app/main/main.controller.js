(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($log, Project, _) {
    var vm = this;

    vm.newProject = newProject;
    vm.editProject = editProject;
    vm.onUpdate = onUpdate;
    vm.onCancel = onCancel;

    loadProjects();

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ', vm.projects);
      });
    }

    function newProject() {
      $log.debug('start creation of new project');
      vm.editing = true;
      vm.project = new Project();
    }

    function editProject(project) {
      $log.debug('editing project: ' + project.id);
      vm.editing = true;
      vm.project = angular.copy(project);
    }

    function onUpdate() {
      vm.editing = false;
      loadProjects();
    }

    function onCancel() {
      vm.editing = false;
    }

  }
})();
