colisAway.service('colisService', ['Restangular', function(Restangular){
	
	this.getElements = function(object){
	  return Restangular.all(object).getList();
	};
	this.getSingle = function(id){
	  return Restangular.one(object, id);
	}
}])