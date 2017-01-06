'use strict';

var module = angular.module('srvModule', ['ngResource']);

module.service("socialSessionSrv", ['$window', socialSessionSrv]);
module.service("sessionSrv", [sessionSrv]);
module.service('valueSrv', [valueSrv]);

module.service("accountService", ['$resource', function ($resource) {
	var service = {};
	service.createAccount = function (account, success, failure) {
		var Account = $resource("/s-dojo-web-app/rest/accounts");
		Account.save({}, account, success, failure);
	}
	return service;
}]);


module.service("sessionService", ['$http', '$cookies', function ($http, $cookies) {
	var session = {};
	session.login = function (data,success,failure) {
		return $http.post("/s-dojo-web-app/login", "username=" + data.username +
			"&password=" + data.password, {
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(function (data) {
			alert("login successful:" + JSON.stringify(data));			
			$cookies.put("session",{});
			if(success)
			{
				success();
			}
		}, function (data) {
			if(failure)
			{
				failure();
			}
		});
	};
	session.logout = function () {
		$cookies.remove("session");
	};
	session.isLoggedIn = function () {
		return $cookies.remove("session") !== null;
	};
	return session;
}]);




function valueSrv() {
	this.homeTitle = "Shooters Dojo";
}

function sessionSrv() {
	this.signedIn = false;
	this.isSignedIn = function () {
		return this.signedIn;
	}

	this.setIsSignedIn = function (value) {
		this.signedIn = value;
	}
}

function socialSessionSrv($window) {
	this.facebookAppId = '412068838867527';
	this.fbLoaded = false;
	this.fbInitialized = false;
	this.fbCBObject = null;
	this.fbCheckLoginStatusReload = false;
	this.fbUserData = {
		status: null,
		accessToken: null,
		userID: null,
		last_name: null,
		first_name: null,
		middle_name: null,
		email: null,
		is_verified: null,
		gender: null
	};

	this.loadFBscripts = function (d, s, id, callbackObject) {
		var js;
		var fjs = d.getElementsByTagName(s)[0];
		if (!d.getElementById(id)) {
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}
		this.fbLoaded = true;
		this.fbCBObject = callbackObject;
	}
	var object = this;
	$window.fbAsyncInit = function () {
		FB.init({
			appId: object.facebookAppId,
			status: false,
			cookie: false,
			xfbml: true,
			version: 'v2.4'
		});
		object.fbInitialized = true;
		object.checkFBLoginStatus();
	}
	this.checkFBLoginStatus = function () {
		var object = this;
		FB.getLoginStatus(
			function (response) {
				object.fbUserData.status = response.status;
				console.log("getstatus");
				console.log(response);
				if (response.status == 'connected') {
					object.fbUserData.userID = response.authResponse.userID;
					object.fbUserData.accessToken = response.authResponse.accessToken;
					object.getFacebookUserData();

				} else if (response.status === 'not_authorized') {
					object.fbUserData.userID = null;
					object.fbUserData.accessToken = null;
					object.fbCBObject.fbStatusCompleteCB();
				} else {
					object.fbUserData.userID = null;
					object.fbUserData.accessToken = null;
					object.fbCBObject.fbStatusCompleteCB();
				}
			},
			object.fbCheckLoginStatusReload

		);
	}
	this.getFacebookUserData = function () {
		var object = this;
		FB.api('/me', {
				fields: 'last_name,first_name,middle_name,email,is_verified,id,gender'
			},
			function (response) {
				if (!response || response.error) {

				} else {
					object.fbUserData.last_name = response.last_name;
					object.fbUserData.first_name = response.first_name;
					object.fbUserData.middle_name = response.middle_name;
					object.fbUserData.email = response.email;
					object.fbUserData.is_verified = response.is_verified;
					object.fbUserData.UserID = response.id;
					object.fbUserData.gender = response.gender;
				}
				object.fbCBObject.fbStatusCompleteCB();
			}
		);
	}
	this.facebookLogout = function () {
		var object = this;
		FB.logout(function (response) {
			if (!response || response.error) {

			} else {

			}
			object.fbUserData.status = 'not_connected';
			object.fbUserData.accessToken = null;
			object.fbUserData.last_name = null;
			object.fbUserData.first_name = null;
			object.fbUserData.middle_name = null;
			object.fbUserData.email = null;
			object.fbUserData.is_verified = null;
			object.fbUserData.UserID = null;
			object.fbUserData.gender = null;
			object.fbCBObject.fbStatusCompleteCB();
		});
	}
	this.facebookLogin = function () {
		var object = this;
		FB.login(function (response) {
			if (response.authResponse) {
				object.fbUserData.status = 'connected';
				object.fbUserData.userID = response.authResponse.userID;
				object.fbUserData.accessToken = response.authResponse.accessToken;
				object.getFacebookUserData();
			} else {
				object.fbUserData.status = 'not_connected';
				object.fbUserData.accessToken = null;
				object.fbUserData.last_name = null;
				object.fbUserData.first_name = null;
				object.fbUserData.middle_name = null;
				object.fbUserData.email = null;
				object.fbUserData.is_verified = null;
				object.fbUserData.UserID = null;
				object.fbUserData.gender = null;
				object.fbCBObject.fbStatusCompleteCB();

			}

		});
	}
}