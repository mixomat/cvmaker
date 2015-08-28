(function () {
  'use strict';

  angular
    .module('cvmaker')
    .factory('Project', ProjectResource);


  function ProjectResource($resource) {
    return $resource('/api/projects/:projectId', {projectId: '@id'},
      {
        all: {method: 'GET', params: {sort: 'start,desc'}, isArray: false},
        update: {method: 'PUT'}
      }
    );
  }

})();
