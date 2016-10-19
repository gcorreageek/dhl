(function() {
    'use strict';

    angular
        .module('proyService1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('article-reaction', {
            parent: 'entity',
            url: '/article-reaction',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyService1App.articleReaction.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/article-reaction/article-reactions.html',
                    controller: 'ArticleReactionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('articleReaction');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('article-reaction-detail', {
            parent: 'entity',
            url: '/article-reaction/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyService1App.articleReaction.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/article-reaction/article-reaction-detail.html',
                    controller: 'ArticleReactionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('articleReaction');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ArticleReaction', function($stateParams, ArticleReaction) {
                    return ArticleReaction.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'article-reaction',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('article-reaction-detail.edit', {
            parent: 'article-reaction-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-reaction/article-reaction-dialog.html',
                    controller: 'ArticleReactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ArticleReaction', function(ArticleReaction) {
                            return ArticleReaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('article-reaction.new', {
            parent: 'article-reaction',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-reaction/article-reaction-dialog.html',
                    controller: 'ArticleReactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                articleReactionType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('article-reaction', null, { reload: 'article-reaction' });
                }, function() {
                    $state.go('article-reaction');
                });
            }]
        })
        .state('article-reaction.edit', {
            parent: 'article-reaction',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-reaction/article-reaction-dialog.html',
                    controller: 'ArticleReactionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ArticleReaction', function(ArticleReaction) {
                            return ArticleReaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('article-reaction', null, { reload: 'article-reaction' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('article-reaction.delete', {
            parent: 'article-reaction',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-reaction/article-reaction-delete-dialog.html',
                    controller: 'ArticleReactionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ArticleReaction', function(ArticleReaction) {
                            return ArticleReaction.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('article-reaction', null, { reload: 'article-reaction' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
