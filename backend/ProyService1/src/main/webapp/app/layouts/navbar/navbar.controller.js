(function() {
    'use strict';

    angular
        .module('proyService1App')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function NavbarController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;
        console.log('haber 3');
        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerEnabled = response.swaggerEnabled;
        });

        vm.login = login;
        vm.logout = logout;
        vm.toggleNavbar = toggleNavbar;
        vm.collapseNavbar = collapseNavbar;
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
            console.log('haber alc ine'+vm.isNavbarCollapsed);
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
            console.log('haber alc ine'+vm.isNavbarCollapsed);
        }

        function toggleNavbar() {
            vm.isNavbarCollapsed = !vm.isNavbarCollapsed;
            console.log('haber alc ine'+vm.isNavbarCollapsed);
        }

        function collapseNavbar() {

            vm.isNavbarCollapsed = true;
            console.log('haber alc ine'+vm.isNavbarCollapsed);
        }
        Principal.identity().then(function(account) {
            vm.account = account;
        });
    }
})();
