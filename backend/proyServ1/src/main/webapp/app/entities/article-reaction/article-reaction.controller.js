(function() {
    'use strict';

    angular
        .module('proyServ1App')
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