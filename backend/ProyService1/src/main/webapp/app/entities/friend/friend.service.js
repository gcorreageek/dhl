(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('Friend', Friend);

    Friend.$inject = ['$resource'];

    function Friend ($resource) {
        var resourceUrl =  'api/friends/:id';

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
