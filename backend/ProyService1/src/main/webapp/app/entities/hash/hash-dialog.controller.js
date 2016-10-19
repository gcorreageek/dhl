(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('HashDialogController', HashDialogController);

    HashDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Hash', 'UserHash', 'ArticleHash', 'User'];

    function HashDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Hash, UserHash, ArticleHash, User) {
        var vm = this;

        vm.hash = entity;
        vm.clear = clear;
        vm.save = save;
        vm.userhashes = UserHash.query();
        vm.articlehashes = ArticleHash.query();
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.hash.id !== null) {
                Hash.update(vm.hash, onSaveSuccess, onSaveError);
            } else {
                Hash.save(vm.hash, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyService1App:hashUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
