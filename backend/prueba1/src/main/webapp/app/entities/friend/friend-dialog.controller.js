(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('FriendDialogController', FriendDialogController);

    FriendDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Friend', 'User'];

    function FriendDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Friend, User) {
        var vm = this;

        vm.friend = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.friend.id !== null) {
                Friend.update(vm.friend, onSaveSuccess, onSaveError);
            } else {
                Friend.save(vm.friend, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('prueba1App:friendUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
