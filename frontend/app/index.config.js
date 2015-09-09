(function () {
  'use strict';

  angular
    .module('cvmaker')
    .config(config);

  /** @ngInject */
  function config($logProvider, $mdThemingProvider, toastr, $authProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // material theme
    $mdThemingProvider.theme('default').primaryPalette('light-blue').accentPalette('pink');

    // Set options third-party lib
    toastr.options.timeOut = 3000;
    toastr.options.positionClass = 'toast-top-right';
    toastr.options.preventDuplicates = true;

    // oauth config
    $authProvider.tokenPrefix = "cvmaker";
    $authProvider.github({
      clientId: '8982f8b1340689811709'
    });
  }

})();
