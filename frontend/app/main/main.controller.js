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
    vm.deleteProject = deleteProject;
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

    function deleteProject(project) {
      Project.delete({projectId: project.id}).$promise.then(onDelete);
    }

    function onSave() {
      vm.editing = false;
      toastr.info('Project saved');
      loadProjects();
    }

    function onCancel() {
      vm.editing = false;
    }

    function onDelete() {
      toastr.info('Project deleted');
      loadProjects();
    }

    }
})();
