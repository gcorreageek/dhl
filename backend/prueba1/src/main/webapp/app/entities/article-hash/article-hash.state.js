(function() {
    'use strict';

    angular
        .module('prueba1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('article-hash', {
            parent: 'entity',
            url: '/article-hash',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ArticleHashes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/article-hash/article-hashes.html',
                    controller: 'ArticleHashController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('article-hash-detail', {
            parent: 'entity',
            url: '/article-hash/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ArticleHash'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/article-hash/article-hash-detail.html',
                    controller: 'ArticleHashDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'ArticleHash', function($stateParams, ArticleHash) {
                    return ArticleHash.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'article-hash',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('article-hash-detail.edit', {
            parent: 'article-hash-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-hash/article-hash-dialog.html',
                    controller: 'ArticleHashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ArticleHash', function(ArticleHash) {
                            return ArticleHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('article-hash.new', {
            parent: 'article-hash',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-hash/article-hash-dialog.html',
                    controller: 'ArticleHashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('article-hash', null, { reload: 'article-hash' });
                }, function() {
                    $state.go('article-hash');
                });
            }]
        })
        .state('article-hash.edit', {
            parent: 'article-hash',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-hash/article-hash-dialog.html',
                    controller: 'ArticleHashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ArticleHash', function(ArticleHash) {
                            return ArticleHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('article-hash', null, { reload: 'article-hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('article-hash.delete', {
            parent: 'article-hash',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/article-hash/article-hash-delete-dialog.html',
                    controller: 'ArticleHashDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ArticleHash', function(ArticleHash) {
                            return ArticleHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('article-hash', null, { reload: 'article-hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
