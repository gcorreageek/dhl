(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('UserPlusDialogController', UserPlusDialogController);

    UserPlusDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserPlus', 'User'];

    function UserPlusDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserPlus, User) {
        var vm = this;

        vm.userPlus = entity;
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
            if (vm.userPlus.id !== null) {
                UserPlus.update(vm.userPlus, onSaveSuccess, onSaveError);
            } else {
                UserPlus.save(vm.userPlus, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyServ1App:userPlusUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
