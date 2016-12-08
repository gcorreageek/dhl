(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserImagenDialogController', UserImagenDialogController);

    UserImagenDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserImagen', 'Article', 'User','$localStorage', '$sessionStorage','Base64','Hash','Principal'];

    function UserImagenDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserImagen, Article, User,$localStorage,$sessionStorage,Base64,Hash,Principal) {
        var vm = this;

        vm.article = {};
        vm.hashsSelected = [];
        // vm.userImagen = entity;
        vm.clear = clear;
        vm.save = save;
        // vm.articles = Article.query();
        vm.users = User.query();
        vm.hashs = Hash.query();




        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            console.log( vm.article );
            console.log( vm.hashsSelected);
            if(vm.article.userImagen.userImagenPathImage ==null || vm.hashsSelected==[]){
                console.log( 'vacios');
                return;
            }else{
                console.log( 'no son vacios');
                console.log( vm.hashsSelected);
                vm.article.articleHashes = [];
                for (var i = 0; i < vm.hashsSelected.length; i++) {
                    vm.article.articleHashes[i] =  {"id":null,"hash":{"id":vm.hashsSelected[i].id},"article":null};
                }
                console.log( vm.article);
                vm.article.userImagen.userImagenName = vm.article.userImagen.userImagenPathImage.filename;
                vm.article.userImagen.userImagenMain = false;
                vm.article.userImagen.userImagenPathImage = vm.article.userImagen.userImagenPathImage.base64;
                vm.article.userImagen.user = vm.account;
                vm.isSaving = true;
                if (vm.article.id != null) {
                    console.log('update');
                    console.log( vm.article );
                    Article.update(vm.article, onSaveSuccess, onSaveError);
                } else {
                    console.log('insert');
                    console.log( vm.article );
                    Article.save(vm.article, onSaveSuccess, onSaveError);
                }
            }

        }

        function onSaveSuccess (result) {
            $scope.$emit('proyService1App:userImagenUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }


    }
})();
