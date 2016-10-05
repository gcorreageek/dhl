(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('ArticleHashDialogController', ArticleHashDialogController);

    ArticleHashDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ArticleHash', 'Hash', 'Article'];

    function ArticleHashDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ArticleHash, Hash, Article) {
        var vm = this;

        vm.articleHash = entity;
        vm.clear = clear;
        vm.save = save;
        vm.hashes = Hash.query();
        vm.articles = Article.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.articleHash.id !== null) {
                ArticleHash.update(vm.articleHash, onSaveSuccess, onSaveError);
            } else {
                ArticleHash.save(vm.articleHash, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyServ1App:articleHashUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
