/* global _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('ProjectFormController', ProjectFormController);

  /** @ngInject */
  function ProjectFormController($scope, $log, $state, toastr, Project, hotkeys) {
    var vm = this, selfLinkPath = '_links.self.href';
    vm.saveProject = saveProject;

    configureHotKeys();
    function configureHotKeys() {
      hotkeys.bindTo($scope).add({
        combo: 'esc',
        description: 'Cancel project edit',
        callback: goToProjectList,
        allowIn: ['INPUT', 'SELECT', 'TEXTAREA']
      });
    }

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
      goToProjectList();
    }

    function goToProjectList() {
      $state.go('projects');
    }

  }

})();
