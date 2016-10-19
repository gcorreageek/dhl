(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('UserImagenController', UserImagenController);

    UserImagenController.$inject = ['$scope', '$state', 'UserImagen'];

    function UserImagenController ($scope, $state, UserImagen) {
        var vm = this;
        
        vm.userImagens = [];

        loadAll();

        function loadAll() {
            UserImagen.query(function(result) {
                vm.userImagens = result;
            });
        }
    }
})();
