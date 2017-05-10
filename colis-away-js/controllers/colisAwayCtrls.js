colisAway.controller('membreController', [ '$scope', 'colisService',
		function($scope, colisService) {

			$scope.salutation = "Liste des utilisateurs";
			colisService.getElements('utilisateurs').then(function(userList) {
				$scope.clients = userList;
			})
		} ]);

colisAway.controller('annonceController', [ '$scope', 'colisService',
		function($scope, colisService) {

			$scope.salutation = "Liste des annonces";
			colisService.getElements('annonces').then(function(annonceList) {
				$scope.annonces = annonceList;
			})
		} ]);

colisAway.controller('messagesController', [ '$scope', 'colisService',
		function($scope, colisService) {

			$scope.salutation = "Liste des messages";
			colisService.getElements('messages').then(function(messagesList) {
				$scope.messages = messagesList;
			})
		} ]);

colisAway.controller('avisController', [ '$scope', 'colisService',
		function($scope, colisService) {

			$scope.salutation = "Liste des avis";
			colisService.getElements('avis').then(function(avisList) {
				$scope.avis = avisList;
			})
		} ]);

colisAway.controller('colisController', [ '$scope', 'colisService',
		function($scope, colisService) {

			$scope.salutation = "Liste des colis";
			colisService.getElements('colis').then(function(colisListe) {
				$scope.colis = colisListe;
			})
		} ]);

colisAway.controller('connexionController', [ '$scope', function($scope){
			
				$scope.connexion="test";
			}]);

colisAway.directive('isDirty', function(){
	return{
		//restrict: 'EAC',
		require: 'ngModel',
		controller: function(scope, element, attrs, ngModel) {
			ngModel.$validators.isDirty = function(value){
				alert(value);
			
			}
		}
	}
})

colisAway.controller('inscriptionController', [ '$scope', function($scope) {
	$scope.name = "Inscription";
} ]);

colisAway.controller('bloggerController', [ '$scope', function($scope) {
	$scope.name = "Blogger";
} ]);

colisAway.controller('promoController', [ '$scope', function($scope) {
	$scope.name = "Bons plans";
} ]);

colisAway.controller('contactController', [ '$scope', function($scope) {
	$scope.name = "Contacter Yeromarley";
} ]);

colisAway.controller('utilisationController', [ '$scope', function($scope) {
	$scope.name = "Comment ça marche";
} ]);
