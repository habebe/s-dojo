'use strict';

angular.module("signIn")
    .component("signIn", {
        templateUrl: 'sign-in/sign-in.template.html',
        controller: ['$scope', '$location', '$log', '$cookies', '$mdToast', 'sessionService', signInCtrl]
    });

function signInCtrl($scope, $location, $log, $cookies, $mdToast, sessionService) {
    this.data = {
        username: null,
        password: null
    };
    this.initializeData = function()
    {
        this.data.username = null;
        this.data.password = null;
    }
    this.initializeData();
    this.requestInProgress = false;
    this.signInDisabled = function()
    {
        return (
            (this.data.username == null) ||
            (this.data.password == null) 
        );
    }
    this.signInClicked = function (event) {
        this.requestInProgress = true;
        var object = this;
        sessionService.login(this.data, function () {
             object.requestInProgress = false;
            $location.path("/dashboard");
        }, function () {
            object.requestInProgress = false;
            $mdToast.show(
                $mdToast.simple()
                .textContent('Invalid credentials')
                .position("bottom right")
                .hideDelay(5000)
            );
        });
    }
    this.forgotPasswordClicked = function (event) {
        $location.path('/forgot-password');
    }
    this.createAccountClicked = function (event) {
        $location.path('/create-account');
    }
}