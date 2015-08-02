(function () {
  'use strict';

  angular.module('cvmaker').factory('Project', function ($resource) {
      return $resource('/projects/:projectId', {projectId: '@id'},
        {
          all: {method: 'GET', isArray: false}
        }
      );
    }
  );

})();
