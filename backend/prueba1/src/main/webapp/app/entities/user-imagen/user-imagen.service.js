(function() {
    'use strict';
    angular
        .module('prueba1App')
        .factory('UserImagen', UserImagen);

    UserImagen.$inject = ['$resource'];

    function UserImagen ($resource) {
        var resourceUrl =  'api/user-imagens/:id';

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
