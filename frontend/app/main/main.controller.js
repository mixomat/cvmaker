(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($log, toastr, Project, _) {
    var vm = this;

    vm.isCreating = false;

    vm.newProject = newProject;
    vm.editProject = editProject;
    vm.saveProject = saveProject;

    loadProjects();

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ' + JSON.stringify(vm.projects, null, 2));
      });
    }

    function newProject() {
      vm.isCreating = true;
      vm.project = new Project();
    }

    function editProject(project) {
      $log.debug('editing project: ' + project.id);
      vm.project = project;
    }

    function saveProject(project) {
      $log.debug('saving project: ', project);

      if (project.id) {
        Project.update({id: project.id}, project);
      } else {
        project.$save().then(handleProjectSave);
        // TODO
        //$scope.post.$save().then(function(response) {
        //  $scope.posts.push(response)
        //});
      }
    }

    function handleProjectSave() {
      resetProject();
      showSaveNotification();
      loadProjects();
    }

    function resetProject() {
      vm.isCreating = false;
      vm.project = new Project();
    }

    function showSaveNotification() {
      toastr.info('Project saved');
    }
  }

})();
