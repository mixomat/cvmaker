(function () {
  'use strict';

  angular
    .module('cvmaker')
    .config(routeConfig);

  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('projects', {
        url: '/projects',
        templateUrl: 'app/components/project/project.html',
        controller: 'ProjectController as project'
      });

    $urlRouterProvider.otherwise("/projects");
  }

})();
