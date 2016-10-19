(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('ArticleReactionDeleteController',ArticleReactionDeleteController);

    ArticleReactionDeleteController.$inject = ['$uibModalInstance', 'entity', 'ArticleReaction'];

    function ArticleReactionDeleteController($uibModalInstance, entity, ArticleReaction) {
        var vm = this;

        vm.articleReaction = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;
        
        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ArticleReaction.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
