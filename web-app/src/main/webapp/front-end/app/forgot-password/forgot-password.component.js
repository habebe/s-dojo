'use strict';

angular.module("forgotPassword")
    .component("forgotPassword", {
        templateUrl: 'forgot-password/forgot-password.template.html',
        controller: ['$scope', '$log', '$cookies', 'valueSrv','sessionSrv', forgotPasswordCtrl]
    });

function forgotPasswordCtrl($scope, $log, $cookies,valueSrv,sessionSrv) {  
    $log.info("forgot-password Ctrl");
}
