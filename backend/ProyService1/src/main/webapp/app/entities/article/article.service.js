(function() {
    'use strict';
    angular
        .module('proyService1App')
        .factory('Article', Article);

    Article.$inject = ['$resource', 'DateUtils'];

    function Article ($resource, DateUtils) {
        var resourceUrl =  'api/articles/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.articleDateTime = DateUtils.convertDateTimeFromServer(data.articleDateTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
