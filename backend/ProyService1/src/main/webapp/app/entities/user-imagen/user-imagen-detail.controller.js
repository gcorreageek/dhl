(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserImagenDetailController', UserImagenDetailController);

    UserImagenDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'UserImagen', 'Article', 'User'];

    function UserImagenDetailController($scope, $rootScope, $stateParams, previousState, entity, UserImagen, Article, User) {
        var vm = this;

        vm.userImagen = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyService1App:userImagenUpdate', function(event, result) {
            vm.userImagen = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
