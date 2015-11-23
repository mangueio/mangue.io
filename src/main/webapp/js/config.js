// config

var app =  
angular.module('app')
  .config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide) {
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
    }
  ])
  .config(['$translateProvider', '$mdThemingProvider', function($translateProvider, $mdThemingProvider){
    // Register a loader for the static files
    // So, the module will search missing translation tables under the specified urls.
    // Those urls are [prefix][langKey][suffix].
    $translateProvider.useStaticFilesLoader({
      prefix: 'l10n/',
      suffix: '.js'
    });
    // Tell the module what language to use by default
    $translateProvider.preferredLanguage('en');
    // Tell the module to store the language in the local storage
    $translateProvider.useLocalStorage();
    $mdThemingProvider.definePalette('amazingPallet1', {"50":"#94b2c7","100":"#618dad","200":"#49718d","300":"#314b5e","400":"#263b4a","500":"#1c2b36","600":"#121b22","700":"#070b0e","800":"#000000","900":"#000000","A100":"#94b2c7","A200":"#618dad","A400":"#263b4a","A700":"#070b0e"});
    $mdThemingProvider.definePalette('amazingPallet2', {"50":"#ffffff","100":"#ffbdbd","200":"#ff8585","300":"#ff3d3d","400":"#ff1f1f","500":"#ff0000","600":"#e00000","700":"#c20000","800":"#a30000","900":"#850000","A100":"#ffffff","A200":"#ffbdbd","A400":"#ff1f1f","A700":"#c20000"});
    $mdThemingProvider.definePalette('amazingPallet3', {"50":"#f1f2f4","100":"#c7ccd1","200":"#a8b0b8","300":"#818c98","400":"#717c8a","500":"#636d79","600":"#555e68","700":"#474f57","800":"#3a3f46","900":"#2c3036","A100":"#f1f2f4","A200":"#c7ccd1","A400":"#717c8a","A700":"#474f57"});
    $mdThemingProvider.theme('default')
    .primaryPalette('amazingPallet1')
    .accentPalette('amazingPallet2', {'default': '400'})
    .warnPalette('amazingPallet3')
  }]);