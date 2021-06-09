angular.module('app', []).controller('ngController', function ($scope, $http) {
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
        $http.delete(contextPath + '/product/' + id)
            .then(function (resp){
                $scope.fillTable();
            })

    };

    $scope.getCategories = function (){
        $http({
            url: contextPath + '/product/categories',
            method: 'GET'
        }).then(function (response){
            $scope.Categories = response.data;
        })
    }

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/product',
            method: 'GET',
            params: {
                'name': $scope.filter ? $scope.filter.name : null,
                'price': $scope.filter ? $scope.filter.price : null,
                'greater': $scope.filter ? $scope.filter.greater : null,
                'less': $scope.filter ? $scope.filter.less : null,
                'category': $scope.filter ? $scope.filter.category : null,
                'page-number': pageIndex
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;

            let minPageIndex = pageIndex - 1;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = pageIndex + 1;
            if (maxPageIndex > $scope.ProductsPage.totalPages) {
                maxPageIndex = $scope.ProductsPage.totalPages;
            }
            $scope.getCategories();
            $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage){
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.fillTable();

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add',
            method: 'PUT',
            params: {
                id: productId
            }
        }).then(function (resp) {
            $scope.updateCart();
        });
    }

    $scope.removeProductFromCart = function (productId) {
        $http({
            url: contextPath + '/cart/remove',
            method: 'DELETE',
            params: {
                id: productId
            }
        }).then(function (resp) {
            $scope.updateCart();
        });
    }

    $scope.updateCart = function () {
        $http.get(contextPath + '/cart/show')
            .then(function (response) {
                $scope.cart=response.data;
                $scope.ProductsInCart=$scope.cart.items;
                $scope.totalCount=$scope.cart.itemsCount;
                $scope.totalPrice=$scope.cart.totalPrice;
            });
    };

    $scope.cleanCart = function () {
        $http.delete(contextPath + '/cart/removeAll')
            .then(function (response) {
                $scope.updateCart();
            });
    };
});