(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('user-plus', {
            parent: 'entity',
            url: '/user-plus',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyServ1App.userPlus.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-plus/user-pluses.html',
                    controller: 'UserPlusController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userPlus');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('user-plus-detail', {
            parent: 'entity',
            url: '/user-plus/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyServ1App.userPlus.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/user-plus/user-plus-detail.html',
                    controller: 'UserPlusDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('userPlus');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'UserPlus', function($stateParams, UserPlus) {
                    return UserPlus.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'user-plus',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('user-plus-detail.edit', {
            parent: 'user-plus-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-plus/user-plus-dialog.html',
                    controller: 'UserPlusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserPlus', function(UserPlus) {
                            return UserPlus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-plus.new', {
            parent: 'user-plus',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-plus/user-plus-dialog.html',
                    controller: 'UserPlusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                info1: null,
                                info2: null,
                                info3: null,
                                info4: null,
                                info5: null,
                                info6: null,
                                info7: null,
                                info8: null,
                                info9: null,
                                info10: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('user-plus', null, { reload: 'user-plus' });
                }, function() {
                    $state.go('user-plus');
                });
            }]
        })
        .state('user-plus.edit', {
            parent: 'user-plus',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-plus/user-plus-dialog.html',
                    controller: 'UserPlusDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['UserPlus', function(UserPlus) {
                            return UserPlus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-plus', null, { reload: 'user-plus' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('user-plus.delete', {
            parent: 'user-plus',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/user-plus/user-plus-delete-dialog.html',
                    controller: 'UserPlusDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['UserPlus', function(UserPlus) {
                            return UserPlus.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('user-plus', null, { reload: 'user-plus' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
