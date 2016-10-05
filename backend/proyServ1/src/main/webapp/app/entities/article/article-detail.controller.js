(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .controller('ArticleDetailController', ArticleDetailController);

    ArticleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Article', 'ArticleHash', 'ArticleReaction', 'User'];

    function ArticleDetailController($scope, $rootScope, $stateParams, previousState, entity, Article, ArticleHash, ArticleReaction, User) {
        var vm = this;

        vm.article = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyServ1App:articleUpdate', function(event, result) {
            vm.article = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
