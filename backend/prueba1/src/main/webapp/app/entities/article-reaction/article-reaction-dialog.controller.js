(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('ArticleReactionDialogController', ArticleReactionDialogController);

    ArticleReactionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ArticleReaction', 'Article', 'User'];

    function ArticleReactionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ArticleReaction, Article, User) {
        var vm = this;

        vm.articleReaction = entity;
        vm.clear = clear;
        vm.save = save;
        vm.articles = Article.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.articleReaction.id !== null) {
                ArticleReaction.update(vm.articleReaction, onSaveSuccess, onSaveError);
            } else {
                ArticleReaction.save(vm.articleReaction, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('prueba1App:articleReactionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
