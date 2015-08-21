(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectController', ProjectController);

  /** @ngInject */
  function ProjectController($log) {
    var vm = this;
    vm.saveProject = saveProject;

    function saveProject() {
      $log.debug('saving project: ', vm.project);

      if (vm.project.id) {
        Project.update({id: vm.project.id}, vm.project).then(vm.onSave);
      } else {
        vm.project.$save().then(vm.onSave);
      }
    }
  }

})();
