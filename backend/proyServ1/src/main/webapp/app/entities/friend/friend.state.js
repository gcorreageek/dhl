(function() {
    'use strict';

    angular
        .module('proyServ1App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('friend', {
            parent: 'entity',
            url: '/friend',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyServ1App.friend.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/friend/friends.html',
                    controller: 'FriendController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('friend');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('friend-detail', {
            parent: 'entity',
            url: '/friend/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyServ1App.friend.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/friend/friend-detail.html',
                    controller: 'FriendDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('friend');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Friend', function($stateParams, Friend) {
                    return Friend.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'friend',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('friend-detail.edit', {
            parent: 'friend-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friend/friend-dialog.html',
                    controller: 'FriendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Friend', function(Friend) {
                            return Friend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('friend.new', {
            parent: 'friend',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friend/friend-dialog.html',
                    controller: 'FriendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                friendType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('friend', null, { reload: 'friend' });
                }, function() {
                    $state.go('friend');
                });
            }]
        })
        .state('friend.edit', {
            parent: 'friend',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friend/friend-dialog.html',
                    controller: 'FriendDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Friend', function(Friend) {
                            return Friend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('friend', null, { reload: 'friend' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('friend.delete', {
            parent: 'friend',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/friend/friend-delete-dialog.html',
                    controller: 'FriendDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Friend', function(Friend) {
                            return Friend.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('friend', null, { reload: 'friend' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
