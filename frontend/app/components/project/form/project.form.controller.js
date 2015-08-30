/* global _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectFormController', ProjectFormController);

  /** @ngInject */
  function ProjectFormController($log, $state, toastr, Project) {
    var vm = this, selfLinkPath = '_links.self.href';
    vm.saveProject = saveProject;


    function saveProject() {
      if (hasId()) {
        $log.debug('updating existing project: ', vm.project);
        Project.update({projectId: getId()}, vm.project).$promise.then(saved);
      } else {
        $log.debug('creating new project: ', vm.project);
        vm.project.$save().then(saved);
      }
    }

    function hasId() {
      return _.has(vm.project, selfLinkPath);
    }

    function getId() {
      var self = _.get(vm.project, selfLinkPath);
      var words = _.words(self, /[^:/]+/g);

      return _.last(words);
    }

    function saved() {
      toastr.info('Project saved');
      $state.go('projects');
    }

  }

})();
