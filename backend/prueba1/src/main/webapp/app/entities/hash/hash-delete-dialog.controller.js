(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('HashDeleteController',HashDeleteController);

    HashDeleteController.$inject = ['$uibModalInstance', 'entity', 'Hash'];

    function HashDeleteController($uibModalInstance, entity, Hash) {
        var vm = this;

        vm.hash = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Hash.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
