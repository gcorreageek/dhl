'use strict';

describe('Controller Tests', function() {

    describe('ArticleHash Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockArticleHash, MockHash, MockArticle;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockArticleHash = jasmine.createSpy('MockArticleHash');
            MockHash = jasmine.createSpy('MockHash');
            MockArticle = jasmine.createSpy('MockArticle');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'ArticleHash': MockArticleHash,
                'Hash': MockHash,
                'Article': MockArticle
            };
            createController = function() {
                $injector.get('$controller')("ArticleHashDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'prueba1App:articleHashUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
