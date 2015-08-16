(function () {
  'use strict';

  angular
    .module('cvmaker')
    .config(config);

  /** @ngInject */
  function config($logProvider, $mdThemingProvider, toastr) {
    // Enable log
    $logProvider.debugEnabled(true);

    // material theme
    $mdThemingProvider.theme('default').primaryPalette('light-blue').accentPalette('amber');

    // Set options third-party lib
    toastr.options.timeOut = 3000;
    toastr.options.positionClass = 'toast-top-right';
    toastr.options.preventDuplicates = true;
  }

})();
