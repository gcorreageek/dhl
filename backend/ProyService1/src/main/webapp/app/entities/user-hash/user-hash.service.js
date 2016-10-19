(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('UserHash', UserHash);

    UserHash.$inject = ['$resource'];

    function UserHash ($resource) {
        var resourceUrl =  'api/user-hashes/:id';

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
