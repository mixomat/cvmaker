(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($log, toastr, Project, _) {
    var vm = this;

    vm.creationDate = 1438285936992;
    vm.showToastr = showToastr;
    vm.editProject = editProject;

    loadProjects();

    function loadProjects() {
      Project.all(function (data) {
        vm.projects = _.get(data, '_embedded.projects', []);
        $log.debug('loaded projects: ' + JSON.stringify(vm.projects, null, 2));
      });
    }

    function showToastr() {
      toastr.info('Hello');
    }

    function editProject(project) {
      $log.debug('editing project: ' + project.id);
    }
  }
})();
