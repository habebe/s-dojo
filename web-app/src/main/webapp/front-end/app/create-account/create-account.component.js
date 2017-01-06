'use strict';

angular.module("createAccount")
    .component("createAccount", {
        templateUrl: 'create-account/create-account.template.html',
        controller: ['$scope', '$log', '$resource', '$location', 'accountService', createAccountCtrl]
    });

function createAccountCtrl($scope, $log, $resource, $location, accountService) {
    this.confirmPassword = null;
    this.requestInProgress = false;

    this.initializeAccountData = function () {
        this.account = {
            firstName: null,
            lastName: null,
            email: null,
            password: null,
            uspsaNumber: null,
            idpaNumber: null
        };
    }
    this.initializeAccountData();

    this.createAccountButtonDisabled = function () {
        return (
            (this.requestInProgress == true) ||
            (this.account.firstName == null) ||
            (this.account.lastName == null) ||
            (this.account.email == null) ||
            (this.account.password == null) ||
            (this.account.password != this.confirmPassword)
        );
    }

    this.createAccountClicked = function ($event) {
        this.requestInProgress = true;
        var object = this;
        accountService.createAccount(this.account, function (data) {
                object.requestInProgress = false;
                object.initializeAccountData();
                $location.path('/sign-in');
            },
            function (error) {
                object.requestInProgress = false;
                if (error.status == 409) {
                    alert("User already exists.");
                } else {
                    alert("Unknown error " + error.status + " " + error.statusText);
                }
            }
        )


    }
}