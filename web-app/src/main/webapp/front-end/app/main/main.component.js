'use strict';

angular.module("main")
    .component("main", {
        templateUrl: 'main/main.template.html',
        controller: ['$scope', '$log', '$cookies', 'valueSrv','sessionSrv', mainCtrl]
    });

function mainCtrl($scope, $log, $cookies,valueSrv,sessionSrv) {
    $scope.initialize = function()
    {
        var value = $cookies.get('main.currentActiveButton');
        if (typeof value == "string") {
            $scope.currentActiveButton = Number(value);
        }
    }

    $scope.setCurrentActiveButton = function (value) {
        $scope.currentActiveButton = value;
        $cookies.put('main.currentActiveButton', $scope.currentActiveButton);
    }

    $scope.homeClicked = function (event) 
    {
       $scope.setCurrentActiveButton(0);
    }

    $scope.dashboardClicked = function (event) 
    {
         $scope.setCurrentActiveButton(1);
    }

    $scope.signInClicked = function(event)
    {
        $log.info("sign in clicked:" + sessionSrv.isSignedIn());
        sessionSrv.setIsSignedIn(!sessionSrv.isSignedIn());
         $scope.setCurrentActiveButton(2);
    }

    

    $scope.homeIsDisabled = function () 
    {
        return ($scope.currentActiveButton == 0);
    }

    $scope.learnMoreIsDisabled = function () 
    {
        return ($scope.currentActiveButton == 1);
    }

    $scope.contentIsHidden = function()
    {
        return ($scope.currentActiveButton == 2);
    }

    $scope.title = valueSrv.homeTitle;
    $scope.currentActiveButton = 0;
    $scope.signedInLabel = "Sign in";
    $scope.initialize();
    $log.info("homeCtrl " + $scope.title + " cookies:" + $cookies);
}
