var shoppingApp = angular.module('shoppingApp',[]);

shoppingApp.controller('basketController', function($scope, $http){
	
	$scope.refreshBasket = function(){
		$http.get('/shoppingonline/rest/basket/items').success(function(data){
			$scope.items = data;
		});
	};
	
	$scope.addToBasket = function(itemId){
		$http.put('/shoppingonline/addtobasket/'+
				itemId).success(function(data){
					alert("Item successfully added to your basket!");
				});
	};
	
	$scope.removeToBasket = function(itemId){
		$http.put('/shoppingonline/removefrombasket/'+
				itemId).success(function(data){
					$scope.refreshBasket();
					alert("Item successfully added to your basket!");
				});
	};
});