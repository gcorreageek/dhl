(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('ArticleHashController', ArticleHashController);

    ArticleHashController.$inject = ['$scope', '$state', 'ArticleHash'];

    function ArticleHashController ($scope, $state, ArticleHash) {
        var vm = this;
        
        vm.articleHashes = [];

        loadAll();

        function loadAll() {
            ArticleHash.query(function(result) {
                vm.articleHashes = result;
            });
        }
    }
})();
