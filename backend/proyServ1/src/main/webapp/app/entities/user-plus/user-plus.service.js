(function() {
    'use strict';
    angular
        .module('proyServ1App')
        .factory('UserPlus', UserPlus);

    UserPlus.$inject = ['$resource'];

    function UserPlus ($resource) {
        var resourceUrl =  'api/user-pluses/:id';

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
