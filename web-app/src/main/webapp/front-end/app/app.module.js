'use strict';

var module = angular.module('app', [
	'ngRoute', 'ngMaterial', 'main','srvModule'
]);

module.controller("appCtrl", [
	'$scope',
	'$log', '$location', '$cookies','valueSrv','sessionSrv',
	appCtrlCB
]);

function appCtrlCB($scope, $log, $location, $cookies, valueSrv,sessionSrv) {
	this.initialize = function () {
		var value = $cookies.get('main.currentActiveButton');
		if (typeof value == "string") {
			this.currentActiveButton = Number(value);
		}
	}

	this.homeClicked = function (event) {
		alert("homeClicked");
		this.setCurrentActiveButton(0);
	}

	this.dashboardClicked = function (event) {
			alert("dashboardClicked");
		this.setCurrentActiveButton(1);
	}

	this.signInClicked = function (event) {
		$location.path("/sign-in");
		sessionSrv.setIsSignedIn(!sessionSrv.isSignedIn());
		this.setCurrentActiveButton(2);
	}



	this.setCurrentActiveButton = function (value) {
		this.currentActiveButton = value;
		$cookies.put('main.currentActiveButton', $scope.currentActiveButton);
	}

	this.title = valueSrv.homeTitle;
	this.currentActiveButton = 0;
	this.signInLabel = "Sign in";
	this.initialize();
	$log.info("homeCtrl " + this.title + " cookies:" + $cookies);
}