(function() {
    'use strict';
    angular
        .module('prueba1App')
        .factory('UserPlus', UserPlus);

    UserPlus.$inject = ['$resource', 'DateUtils'];

    function UserPlus ($resource, DateUtils) {
        var resourceUrl =  'api/user-pluses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.birthday = DateUtils.convertDateTimeFromServer(data.birthday);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
