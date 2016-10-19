(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('HashController', HashController);

    HashController.$inject = ['$scope', '$state', 'Hash'];

    function HashController ($scope, $state, Hash) {
        var vm = this;
        
        vm.hashes = [];

        loadAll();

        function loadAll() {
            Hash.query(function(result) {
                vm.hashes = result;
            });
        }
    }
})();
