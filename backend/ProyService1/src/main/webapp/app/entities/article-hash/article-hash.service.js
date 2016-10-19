(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('ArticleHash', ArticleHash);

    ArticleHash.$inject = ['$resource'];

    function ArticleHash ($resource) {
        var resourceUrl =  'api/article-hashes/:id';

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
