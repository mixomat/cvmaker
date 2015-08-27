(function () {
  'use strict';

  angular
    .module('cvmaker')
    .config(routeConfig);

  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('projects', {
        url: '/projects',
        templateUrl: 'app/main/main.html',
        controller: 'MainController as main'
      });

    $urlRouterProvider.otherwise("/projects");
  }

})();
