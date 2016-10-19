(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('UserImagenDeleteController',UserImagenDeleteController);

    UserImagenDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserImagen'];

    function UserImagenDeleteController($uibModalInstance, entity, UserImagen) {
        var vm = this;

        vm.userImagen = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserImagen.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
