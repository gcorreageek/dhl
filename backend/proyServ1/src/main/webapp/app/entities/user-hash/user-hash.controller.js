(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('UserHashController', UserHashController);

    UserHashController.$inject = ['$scope', '$state', 'UserHash'];

    function UserHashController ($scope, $state, UserHash) {
        var vm = this;
        
        vm.userHashes = [];

        loadAll();

        function loadAll() {
            UserHash.query(function(result) {
                vm.userHashes = result;
            });
        }
    }
})();
