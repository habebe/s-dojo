'use strict';

angular.
module('app').
config(['$locationProvider' ,'$routeProvider',
	function config($locationProvider, $routeProvider) {
	$locationProvider.hashPrefix('!');

	$routeProvider.
	when('/sign-in', {
		template: '<sign-in></sign-in>'
	}).
	when('/create-account', {
		template: '<create-account></create-account>'
	}).
	when('/forgot-password', {
		template: '<forgot-password></forgot-password>'
	}).
	when('/unknown', {
		template: '<div><label>Unknown</label></div>'
	}).
	
	otherwise('/main');
	
}
]);
