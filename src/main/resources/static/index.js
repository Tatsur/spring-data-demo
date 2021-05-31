angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/shop/api/v1';

    $scope.saveProduct = function () {
        console.log($scope.newProduct)
        $http.post(contextPath + '/product', $scope.newProduct)
            .then(function (resp){
                $scope.newProduct = null
                $scope.fillTable();
            })

    };

    $scope.deleteProduct = function (id) {
        console.log('product id: '+id)
        $http.delete(contextPath + '/product/delete/'+id)
            .then(function (resp){
                $scope.fillTable();
            })

    };

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/product',
            method: 'GET',
            params: {
                'name': $scope.filter ? $scope.filter.name : null,
                'price': $scope.filter ? $scope.filter.price : null,
                'greater': $scope.filter ? $scope.filter.greater : null,
                'less': $scope.filter ? $scope.filter.less : null
            }
        }).then(function (response) {
            $scope.Products = response.data;
        });
    };

    $scope.fillTable();
});