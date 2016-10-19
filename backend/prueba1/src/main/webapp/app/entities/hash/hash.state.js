(function() {
    'use strict';

    angular
        .module('prueba1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('hash', {
            parent: 'entity',
            url: '/hash',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Hashes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/hash/hashes.html',
                    controller: 'HashController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('hash-detail', {
            parent: 'entity',
            url: '/hash/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Hash'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/hash/hash-detail.html',
                    controller: 'HashDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Hash', function($stateParams, Hash) {
                    return Hash.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'hash',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('hash-detail.edit', {
            parent: 'hash-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hash/hash-dialog.html',
                    controller: 'HashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Hash', function(Hash) {
                            return Hash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('hash.new', {
            parent: 'hash',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hash/hash-dialog.html',
                    controller: 'HashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                hashName: null,
                                hashType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('hash', null, { reload: 'hash' });
                }, function() {
                    $state.go('hash');
                });
            }]
        })
        .state('hash.edit', {
            parent: 'hash',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hash/hash-dialog.html',
                    controller: 'HashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Hash', function(Hash) {
                            return Hash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('hash', null, { reload: 'hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('hash.delete', {
            parent: 'hash',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hash/hash-delete-dialog.html',
                    controller: 'HashDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Hash', function(Hash) {
                            return Hash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('hash', null, { reload: 'hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
