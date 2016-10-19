(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('UserPlusController', UserPlusController);

    UserPlusController.$inject = ['$scope', '$state', 'UserPlus'];

    function UserPlusController ($scope, $state, UserPlus) {
        var vm = this;
        
        vm.userPluses = [];

        loadAll();

        function loadAll() {
            UserPlus.query(function(result) {
                vm.userPluses = result;
            });
        }
    }
})();
