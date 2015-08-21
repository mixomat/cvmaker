(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('projectForm', ProjectForm);

  /** @ngInject */
  function ProjectForm() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/project/project.form.html',
      scope: true,
      bindToController: {
        project: '=',
        onSave: '&',
        onCancel: '&'
      },
      controller: 'ProjectController as ctrl'
    };
  }
})();
