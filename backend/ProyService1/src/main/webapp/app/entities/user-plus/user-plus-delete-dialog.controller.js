(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserPlusDeleteController',UserPlusDeleteController);

    UserPlusDeleteController.$inject = ['$uibModalInstance', 'entity', 'UserPlus'];

    function UserPlusDeleteController($uibModalInstance, entity, UserPlus) {
        var vm = this;

        vm.userPlus = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            UserPlus.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
