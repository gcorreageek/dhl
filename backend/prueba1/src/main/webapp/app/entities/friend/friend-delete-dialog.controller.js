(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('FriendDeleteController',FriendDeleteController);

    FriendDeleteController.$inject = ['$uibModalInstance', 'entity', 'Friend'];

    function FriendDeleteController($uibModalInstance, entity, Friend) {
        var vm = this;

        vm.friend = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Friend.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
