colisAway.config(function(RestangularProvider){
	var urlBase = "http://localhost:8080/colis-away-service/";
		
		RestangularProvider.setBaseUrl(urlBase);
		
});

colisAway.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
	$routeProvider
	.when('/', {
		templateUrl: 'views/accueil.html',
		controller: 'annonceController'
	})
	.when('/membres', {
		templateUrl: 'views/membres.html',
		controller: 'membreController'
	})
	.when('/blogger', {
		templateUrl: 'views/blogger.html',
		controller: 'bloggerController'
	})
		.when('/utilisation', {
		templateUrl: 'views/utilisation.html',
		controller: 'utilisationController'
	})
	.when('/contact', {
		templateUrl: 'views/contact.html',
		controller: 'contactController'
	})
	.when('/flash_promo', {
		templateUrl: 'views/bons_plans.html',
		controller: 'promoController'
	})
	.when('/connexion', {
		templateUrl: 'views/connexion.html',
		controller: 'connexionController'
	})
	.when('/inscription', {
		templateUrl: 'views/inscription.html',
		controller: 'inscriptionController'
	})
	
	$locationProvider.html5Mode(true);
}])

