/* global toastr:false, moment:false, _:false */
(function () {
  'use strict';

  angular
    .module('cvmaker')
    .constant('toastr', toastr)
    .constant('_', _)
    .constant('moment', moment);

})();
