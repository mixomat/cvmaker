(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('projectForm', ProjectForm);

  /** @ngInject */
  function ProjectForm() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/project/form/project.form.html',
      scope: true,
      bindToController: {
        project: '=',
        onUpdate: '=',
        onCancel: '='
      },
      controller: 'ProjectFormController as ctrl'
    };
  }
})();
