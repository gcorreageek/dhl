(function() {
    'use strict';

    angular
        .module('prueba1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-imagen', {
            parent: 'entity',
            url: '/user-imagen',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserImagens'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-imagen/user-imagens.html',
                    controller: 'UserImagenController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('user-imagen-detail', {
            parent: 'entity',
            url: '/user-imagen/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'UserImagen'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-imagen/user-imagen-detail.html',
                    controller: 'UserImagenDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'UserImagen', function($stateParams, UserImagen) {
                    return UserImagen.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-imagen',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-imagen-detail.edit', {
            parent: 'user-imagen-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-imagen/user-imagen-dialog.html',
                    controller: 'UserImagenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserImagen', function(UserImagen) {
                            return UserImagen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-imagen.new', {
            parent: 'user-imagen',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-imagen/user-imagen-dialog.html',
                    controller: 'UserImagenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                userImagenName: null,
                                userImagenPath: null,
                                userImagenPathImage: null,
                                userImagenMain: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-imagen', null, { reload: 'user-imagen' });
                }, function() {
                    $state.go('user-imagen');
                });
            }]
        })
        .state('user-imagen.edit', {
            parent: 'user-imagen',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-imagen/user-imagen-dialog.html',
                    controller: 'UserImagenDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserImagen', function(UserImagen) {
                            return UserImagen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-imagen', null, { reload: 'user-imagen' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-imagen.delete', {
            parent: 'user-imagen',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-imagen/user-imagen-delete-dialog.html',
                    controller: 'UserImagenDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserImagen', function(UserImagen) {
                            return UserImagen.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-imagen', null, { reload: 'user-imagen' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
