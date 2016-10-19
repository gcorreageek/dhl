(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('ArticleReaction', ArticleReaction);

    ArticleReaction.$inject = ['$resource'];

    function ArticleReaction ($resource) {
        var resourceUrl =  'api/article-reactions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
