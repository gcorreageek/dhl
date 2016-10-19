(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('ArticleHashDeleteController',ArticleHashDeleteController);

    ArticleHashDeleteController.$inject = ['$uibModalInstance', 'entity', 'ArticleHash'];

    function ArticleHashDeleteController($uibModalInstance, entity, ArticleHash) {
        var vm = this;

        vm.articleHash = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ArticleHash.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
