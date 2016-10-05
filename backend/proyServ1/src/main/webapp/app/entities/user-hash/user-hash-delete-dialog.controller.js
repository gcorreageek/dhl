(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('UserHashDeleteController',UserHashDeleteController);

    UserHashDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserHash'];

    function UserHashDeleteController($uibModalInstance, entity, UserHash) {
        var vm = this;

        vm.userHash = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserHash.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
