(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('UserImagenDetailController', UserImagenDetailController);

    UserImagenDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserImagen', 'User'];

    function UserImagenDetailController($scope, $rootScope, $stateParams, previousState, entity, UserImagen, User) {
        var vm = this;

        vm.userImagen = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyServ1App:userImagenUpdate', function(event, result) {
            vm.userImagen = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
