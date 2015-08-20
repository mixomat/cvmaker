(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($log, toastr, Project, _) {
    var vm = this;

    vm.newProject = newProject;
    vm.editProject = editProject;
    vm.onSave = onSave;
    vm.onCancel = onCancel;

    loadProjects();

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ', vm.projects);
      });
    }

    function newProject() {
      $log.debug('creating new project');
      vm.editing = true;
      vm.project = new Project();
    }

    function editProject(project) {
      $log.debug('editing project: ' + project.id);
      vm.editing = true;
      vm.project = project;
    }

    function onSave() {
      vm.editing = false;
      showSaveNotification();
      loadProjects();
    }

    function onCancel() {
      vm.editing = false;
    }

    function showSaveNotification() {
      toastr.info('Project saved');
    }
  }
})();
