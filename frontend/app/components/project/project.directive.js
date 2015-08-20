(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('cvProject', cvProject);

  /** @ngInject */
  function cvProject() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/project/project.html',
      scope: true,
      bindToController: {
        project: '=',
        onSave: '&',
        onCancel: '&'
      },
      controller: ProjectCtrl,
      controllerAs: 'ctrl'
    };
  }

  function ProjectCtrl($log, Project) {
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
