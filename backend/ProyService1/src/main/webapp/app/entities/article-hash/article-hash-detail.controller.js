(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('ArticleHashDetailController', ArticleHashDetailController);

    ArticleHashDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ArticleHash', 'Hash', 'Article'];

    function ArticleHashDetailController($scope, $rootScope, $stateParams, previousState, entity, ArticleHash, Hash, Article) {
        var vm = this;

        vm.articleHash = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyService1App:articleHashUpdate', function(event, result) {
            vm.articleHash = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
