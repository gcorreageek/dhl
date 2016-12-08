(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserImagenController', UserImagenController);

    UserImagenController.$inject = ['$scope', '$state', 'UserImagen','Principal','Article','UserPlus','Hash','User'];

    function UserImagenController ($scope, $state, UserImagen,Principal,Article,UserPlus,Hash,User) {
        var vm = this;

        vm.savePlus = savePlus;
        vm.saveHash = saveHash;


        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;

        // vm.account.userPlus = UserPlus.get({id : $stateParams.id}).$promise;

        vm.articles = Article.articlesuser({id: 'ofuser'});
        vm.hashs = Hash.query();


        // vm.articles = Article.articlesuser({"prueba":"1"});
        vm.userImagens = [];
        vm.viewTimeline = false;
        vm.viewInfo = false;
        vm.viewHash = false;
        vm.viewFriends = false;

        vm.showTimeline = function(){
            vm.viewTimeline = true;
            vm.viewInfo = false;
            vm.viewHash = false;
            vm.viewFriends = false;
        };
        vm.showInfo = function(){
            vm.viewTimeline = false;
            vm.viewInfo = true;
            vm.viewHash = false;
            vm.viewFriends = false;
        };
        vm.showHash = function(){
            vm.viewTimeline = false;
            vm.viewInfo = false;
            vm.viewHash = true;
            vm.viewFriends = false;
        };
        vm.showFriends = function(){
            vm.viewTimeline = false;
            vm.viewInfo = false;
            vm.viewHash = false;
            vm.viewFriends = true;
        };
        vm.hisHash = null;
        vm.myHash = null;
        vm.plus = function(){
            if(vm.hisHash!=null){
                for (var i = 0; i < vm.hisHash.length; i++) {
                    if(!haveRegisteredMyHash(vm.hisHash[i])){
                        vm.account.userHash.push({"id":null,"hash":{"id":vm.hisHash[i].id,"hashName":vm.hisHash[i].hashName,"hashType":vm.hisHash[i].hashType}});
                    }
                }
            }
        };



        vm.minus = function(){
            if(vm.myHash!=null){
                for (var i = 0; i < vm.myHash.length; i++) {
                    deleteMyHash(vm.myHash[i]);
                }
            }
        };

        function deleteMyHash(hash) {
            console.log(hash);
            for (var i = 0; i < vm.account.userHash.length; i++) {
                if(hash.hash.id==vm.account.userHash[i].hash.id){
                    vm.account.userHash.splice(i,1);
                    break;
                }
            }
        }

        function haveRegisteredMyHash(hash) {
            console.log(hash);
            for (var i = 0; i < vm.account.userHash.length; i++) {
                console.log(vm.account.userHash[i].hash.id);
                if(hash.id==vm.account.userHash[i].hash.id){
                    return true;
                }
            }
            return false;
        }

        loadAll();


        function loadAll() {
            console.log(vm.articles);
            vm.showTimeline();
            UserImagen.query(function(result) {
                vm.userImagens = result;
            });
            Principal.identity().then(function(account) {
                vm.account = account;
            });
        }
        function savePlus () {
            vm.account.userPlus.user = vm.account.user;
            console.log(vm.account.userPlus.user );
            UserPlus.update(vm.account.userPlus);
        }
        function saveHash(){

            for (var i = 0; i < vm.account.userHash.length; i++) {
                vm.account.userHash[i].user = vm.account.user;
            }

            User.update(vm.account);

        }

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }


        vm.datePickerOpenStatus.birthday = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }


    }
})();
