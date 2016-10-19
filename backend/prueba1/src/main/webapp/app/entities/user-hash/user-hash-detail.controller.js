(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('UserHashDetailController', UserHashDetailController);

    UserHashDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserHash', 'Hash', 'User'];

    function UserHashDetailController($scope, $rootScope, $stateParams, previousState, entity, UserHash, Hash, User) {
        var vm = this;

        vm.userHash = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prueba1App:userHashUpdate', function(event, result) {
            vm.userHash = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
