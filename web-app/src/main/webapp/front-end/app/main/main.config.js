'use strict';

angular.
module('main').
config(['$locationProvider' ,'$routeProvider',
	function config($locationProvider, $routeProvider) {
	$locationProvider.hashPrefix('!');

	$routeProvider.
	when('/home', {
		template: '<home></home>'
	}).
	when('/unknown', {
		template: '<div><label>Unknown</label></div>'
	}).
	
	otherwise('/home');
	
}
]);
