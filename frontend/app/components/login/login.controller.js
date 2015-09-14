(function () {
  'use strict';

  angular
    .module('cvmaker')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController($log, $auth, toastr, $location) {
    var vm = this;
    vm.authenticate = authenticate;

    function authenticate(provider) {
      $auth.authenticate(provider)
        .then(function () {
          toastr.success('You have successfully signed in with ' + provider);
          $location.path('/');
        })
        .catch(function (response) {
          toastr.error(response.data.message, response.status);
        });
    }

  }

})();
