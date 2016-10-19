'use strict';

describe('Controller Tests', function() {

    describe('UserImagen Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockUserImagen, MockArticle, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockUserImagen = jasmine.createSpy('MockUserImagen');
            MockArticle = jasmine.createSpy('MockArticle');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'UserImagen': MockUserImagen,
                'Article': MockArticle,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("UserImagenDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'proyService1App:userImagenUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
