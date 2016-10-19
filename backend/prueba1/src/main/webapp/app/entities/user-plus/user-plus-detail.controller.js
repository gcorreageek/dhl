(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('UserPlusDetailController', UserPlusDetailController);

    UserPlusDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserPlus', 'User'];

    function UserPlusDetailController($scope, $rootScope, $stateParams, previousState, entity, UserPlus, User) {
        var vm = this;

        vm.userPlus = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prueba1App:userPlusUpdate', function(event, result) {
            vm.userPlus = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();