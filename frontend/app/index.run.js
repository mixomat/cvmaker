(function () {
  'use strict';

  angular
    .module('cvmaker')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log) {
    $log.debug('cvmaker started');
  }

})();
