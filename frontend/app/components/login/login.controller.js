(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($log, $auth) {
    var vm = this;
    vm.authenticate = authenticate;

    function authenticate() {
      $auth.authenticate('github');
    }

  }

})();
