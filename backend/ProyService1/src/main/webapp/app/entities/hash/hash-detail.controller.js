(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('HashDetailController', HashDetailController);

    HashDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Hash', 'UserHash', 'ArticleHash', 'User'];

    function HashDetailController($scope, $rootScope, $stateParams, previousState, entity, Hash, UserHash, ArticleHash, User) {
        var vm = this;

        vm.hash = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyService1App:hashUpdate', function(event, result) {
            vm.hash = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
