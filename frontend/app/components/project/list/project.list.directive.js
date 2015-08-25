(function () {
  'use strict';

  angular
    .module('cvmaker')
    .directive('projectList', ProjectList);

  /** @ngInject */
  function ProjectList() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/project/list/project.list.html',
      scope: true,
      bindToController: {
        projects: '=',
        onEdit: '=',
        onUpdate: '='
      },
      controller: 'ProjectListController as ctrl'
    };
  }
})();
