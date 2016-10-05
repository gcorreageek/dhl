(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('ArticleController', ArticleController);

    ArticleController.$inject = ['$scope', '$state', 'Article'];

    function ArticleController ($scope, $state, Article) {
        var vm = this;
        
        vm.articles = [];

        loadAll();

        function loadAll() {
            Article.query(function(result) {
                vm.articles = result;
            });
        }
    }
})();
