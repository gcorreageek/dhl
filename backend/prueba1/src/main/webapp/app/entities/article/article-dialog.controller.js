(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('ArticleDialogController', ArticleDialogController);

    ArticleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Article', 'UserImagen', 'ArticleHash', 'ArticleReaction', 'User'];

    function ArticleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Article, UserImagen, ArticleHash, ArticleReaction, User) {
        var vm = this;

        vm.article = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.userimagens = UserImagen.query();
        vm.articlehashes = ArticleHash.query();
        vm.articlereactions = ArticleReaction.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.article.id !== null) {
                Article.update(vm.article, onSaveSuccess, onSaveError);
            } else {
                Article.save(vm.article, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('prueba1App:articleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.articleDateTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
