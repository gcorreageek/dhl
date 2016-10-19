(function() {
    'use strict';

    angular
        .module('prueba1App')
        .controller('ArticleReactionDetailController', ArticleReactionDetailController);

    ArticleReactionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ArticleReaction', 'Article', 'User'];

    function ArticleReactionDetailController($scope, $rootScope, $stateParams, previousState, entity, ArticleReaction, Article, User) {
        var vm = this;

        vm.articleReaction = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prueba1App:articleReactionUpdate', function(event, result) {
            vm.articleReaction = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
