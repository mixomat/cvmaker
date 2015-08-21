(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectController', ProjectController);

  /** @ngInject */
  function ProjectController($log, Project) {
    var vm = this;
    vm.saveProject = saveProject;

    function saveProject() {
      $log.debug('saving project: ', vm.project);

      if (vm.project.id) {
        Project.update(vm.project).$promise.then(vm.onSave);
      } else {
        vm.project.$save().then(vm.onSave);
      }
    }
  }

})();
