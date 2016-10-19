(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserHashDialogController', UserHashDialogController);

    UserHashDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserHash', 'Hash', 'User'];

    function UserHashDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserHash, Hash, User) {
        var vm = this;

        vm.userHash = entity;
        vm.clear = clear;
        vm.save = save;
        vm.hashes = Hash.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.userHash.id !== null) {
                UserHash.update(vm.userHash, onSaveSuccess, onSaveError);
            } else {
                UserHash.save(vm.userHash, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyService1App:userHashUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
