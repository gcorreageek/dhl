(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('UserImagenDialogController', UserImagenDialogController);

    UserImagenDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserImagen', 'User'];

    function UserImagenDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserImagen, User) {
        var vm = this;

        vm.userImagen = entity;
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
            if (vm.userImagen.id !== null) {
                UserImagen.update(vm.userImagen, onSaveSuccess, onSaveError);
            } else {
                UserImagen.save(vm.userImagen, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyServ1App:userImagenUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
