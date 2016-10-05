(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('ArticleReactionDetailController', ArticleReactionDetailController);

    ArticleReactionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ArticleReaction', 'Article', 'User'];

    function ArticleReactionDetailController($scope, $rootScope, $stateParams, previousState, entity, ArticleReaction, Article, User) {
        var vm = this;

        vm.articleReaction = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyServ1App:articleReactionUpdate', function(event, result) {
            vm.articleReaction = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
