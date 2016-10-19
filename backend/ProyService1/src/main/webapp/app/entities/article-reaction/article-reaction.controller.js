(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('ArticleReactionController', ArticleReactionController);

    ArticleReactionController.$inject = ['$scope', '$state', 'ArticleReaction'];

    function ArticleReactionController ($scope, $state, ArticleReaction) {
        var vm = this;
        
        vm.articleReactions = [];

        loadAll();

        function loadAll() {
            ArticleReaction.query(function(result) {
                vm.articleReactions = result;
            });
        }
    }
})();
