(function () {
  'use strict';

  angular
    .module('cvmaker')
    .config(routeConfig);

  function routeConfig($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('login', {
        url: '/login',
        templateUrl: 'app/components/login/login.html',
        controller: 'LoginController as login',
        resolve: {
          skipIfLoggedIn: skipIfLoggedIn
        }
      })
      .state('projects', {
        url: '/projects',
        templateUrl: 'app/components/project/list/project.list.html',
        controller: 'ProjectListController as projectList',
        resolve: {
          loginRequired: loginRequired
        }
      })
      .state('projectEdit', {
        url: '/projects/:projectId/edit',
        resolve: {
          loginRequired: loginRequired,
          project: ['$stateParams', 'Project', function ($stateParams, Project) {
            if ($stateParams.projectId) {
              return Project.get({projectId: $stateParams.projectId}).$promise;
            }
            else {
              return new Project({technologies: []});
            }
          }]
        },
        templateUrl: 'app/components/project/edit/project.edit.html',
        controller: 'ProjectEditController as projectEdit'
      });

    $urlRouterProvider.otherwise("/projects");
  }

  function skipIfLoggedIn($q, $location, $auth) {
    var deferred = $q.defer();
    if ($auth.isAuthenticated()) {
      $location.path('/');
    } else {
      deferred.resolve();
    }
    return deferred.promise;
  }

  function loginRequired($q, $location, $auth) {
    var deferred = $q.defer();
    if ($auth.isAuthenticated()) {
      deferred.resolve();
    } else {
      $location.path('/login');
    }
    return deferred.promise;
  }
})();
