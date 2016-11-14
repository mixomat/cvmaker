/* global _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectListController', ProjectListController);

  /** @ngInject */
  function ProjectListController($scope, $log, $anchorScroll, toastr, Project, $state, hotkeys) {
    var vm = this, selectedProjectIndex = -1;
    vm.deleteProject = deleteProject;
    vm.selectNextProject = selectNextProject;
    vm.selectPreviousProject = selectPreviousProject;
    vm.isSelectedProject = isSelectedProject;

    configureHotKeys();
    loadProjects();

    function configureHotKeys() {
      hotkeys.bindTo($scope)
        .add({
          combo: 'c',
          description: 'Create a new project',
          callback: createNewProject
        }).add({
          combo: 'e',
          description: 'Edit selected project',
          callback: editSelectedProject
        }).add({
          combo: 'j',
          description: 'Select next project',
          callback: selectNextProject
        }).add({
          combo: 'k',
          description: 'Select previous project',
          callback: selectPreviousProject
        });
    }

    function selectNextProject() {
      selectedProjectIndex = Math.min(++selectedProjectIndex, vm.projects.length - 1);
      scrollToProject(selectedProjectIndex);
    }

    function selectPreviousProject() {
      selectedProjectIndex = Math.max(--selectedProjectIndex, -1);
      scrollToProject(selectedProjectIndex);
    }

    function scrollToProject(index) {
      if (index === -1) {
        $anchorScroll('navbar');
      }
      else {
        $anchorScroll('project-' + index);
      }
    }

    function isSelectedProject(index) {
      return selectedProjectIndex === index;
    }

    function createNewProject() {
      $state.go('projectEdit');
    }

    function editSelectedProject() {
      if (selectedProjectIndex > -1) {
        var projectId = vm.projects[selectedProjectIndex].id;
        $state.go('projectEdit', {projectId: projectId});
      }
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
