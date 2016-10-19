(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('Hash', Hash);

    Hash.$inject = ['$resource'];

    function Hash ($resource) {
        var resourceUrl =  'api/hashes/:id';

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
