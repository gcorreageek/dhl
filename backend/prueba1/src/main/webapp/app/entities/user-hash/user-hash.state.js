(function() {
    'use strict';

    angular
        .module('prueba1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-hash', {
            parent: 'entity',
            url: '/user-hash',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserHashes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-hash/user-hashes.html',
                    controller: 'UserHashController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('user-hash-detail', {
            parent: 'entity',
            url: '/user-hash/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserHash'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-hash/user-hash-detail.html',
                    controller: 'UserHashDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'UserHash', function($stateParams, UserHash) {
                    return UserHash.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-hash',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-hash-detail.edit', {
            parent: 'user-hash-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-hash/user-hash-dialog.html',
                    controller: 'UserHashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserHash', function(UserHash) {
                            return UserHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-hash.new', {
            parent: 'user-hash',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-hash/user-hash-dialog.html',
                    controller: 'UserHashDialogController',
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
                    $state.go('user-hash', null, { reload: 'user-hash' });
                }, function() {
                    $state.go('user-hash');
                });
            }]
        })
        .state('user-hash.edit', {
            parent: 'user-hash',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-hash/user-hash-dialog.html',
                    controller: 'UserHashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserHash', function(UserHash) {
                            return UserHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-hash', null, { reload: 'user-hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-hash.delete', {
            parent: 'user-hash',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-hash/user-hash-delete-dialog.html',
                    controller: 'UserHashDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserHash', function(UserHash) {
                            return UserHash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-hash', null, { reload: 'user-hash' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
