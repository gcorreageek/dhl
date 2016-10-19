(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('FriendController', FriendController);

    FriendController.$inject = ['$scope', '$state', 'Friend'];

    function FriendController ($scope, $state, Friend) {
        var vm = this;
        
        vm.friends = [];

        loadAll();

        function loadAll() {
            Friend.query(function(result) {
                vm.friends = result;
            });
        }
    }
})();
