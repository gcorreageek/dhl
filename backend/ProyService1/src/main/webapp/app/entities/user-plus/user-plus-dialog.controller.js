(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserPlusDialogController', UserPlusDialogController);

    UserPlusDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'UserPlus', 'User','Principal' ];

    // HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state'];


    function UserPlusDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, UserPlus, User,Principal) {
        var vm = this;
        console.log('la conhateuymfe');
        Principal.identity().then(function (user) {
            var data = {
                user: user
            };
            console.log(data);
            console.log(data.user.authorities);
            vm.users = User.query({auth:data.user.authorities[0]});
        });
        console.log(UserPlus);
        vm.userPlus = entity;
        // console.log(entity2);
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;





        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;



            if (vm.userPlus.id !== null) {
                UserPlus.update(vm.userPlus, onSaveSuccess, onSaveError);
            } else {
                UserPlus.save(vm.userPlus, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyService1App:userPlusUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.birthday = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }


    }
})();
