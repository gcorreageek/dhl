(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('FriendDetailController', FriendDetailController);

    FriendDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Friend', 'User'];

    function FriendDetailController($scope, $rootScope, $stateParams, previousState, entity, Friend, User) {
        var vm = this;

        vm.friend = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prueba1App:friendUpdate', function(event, result) {
            vm.friend = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
