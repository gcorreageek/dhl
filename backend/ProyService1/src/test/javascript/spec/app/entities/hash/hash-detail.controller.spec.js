'use strict';

describe('Controller Tests', function() {

    describe('Hash Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockHash, MockUserHash, MockArticleHash, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockHash = jasmine.createSpy('MockHash');
            MockUserHash = jasmine.createSpy('MockUserHash');
            MockArticleHash = jasmine.createSpy('MockArticleHash');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Hash': MockHash,
                'UserHash': MockUserHash,
                'ArticleHash': MockArticleHash,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("HashDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'proyService1App:hashUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
