// config

var app =  
angular.module('app')
  .constant('MANGUE', {
    baseUrl: location.protocol + '//' + location.host,
    //baseUrl: 'http://master.com',
    pageSize: 100
  })
  .config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide', 'cfpLoadingBarProvider', '$httpProvider', 'MANGUE',
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide ,  cfpLoadingBarProvider ,  $httpProvider ,  MANGUE) {

        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.interceptors.push(function trix_httpInterceptor($q, $rootScope) {
            var requestInterceptor = {
                responseError: function(rejection){
                    $rootScope.$broadcast('HTTP_ERROR',rejection);
                    return $q.reject(rejection);
                },
                // optional method
                response: function(response) {
                // do something on success
                if (MANGUE.baseUrl && response.config.method === "GET" &&
                    (response.config.url.indexOf(MANGUE.baseUrl + "/api") > -1 || response.config.url.indexOf(MANGUE.baseUrl + "/data") > -1)  &&
                    response.data && response.data._embedded){
                    response.data = response.data._embedded;
                return response
            }else if (MANGUE.baseUrl && response.config.method === "GET" &&
                (response.config.url.indexOf(MANGUE.baseUrl + "/api") > -1 || response.config.url.indexOf(MANGUE.baseUrl + "/data") > -1) > -1 &&
                response.data && response.data.content){
                response.data = response.data.content;
                return response
            }else if(response.config.method === "POST" || response.config.method === "PUT"){
                var _value = response.headers("Location");
                if (_value) {
                    var _index = _value.lastIndexOf("/");
                    var _suffix = _value.substring(_index + 1);
                    var id = _suffix;
                    response.data = id
                }
                return response;
            }else
            return response;
        },
        request: function(config) {
          return config;
        }
        };
        return requestInterceptor;
    })

    delete $httpProvider.defaults.headers.common['X-Requested-With'];
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;

        //cfpLoadingBarProvider.includeSpinner = false;
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

    $mdThemingProvider.definePalette('Pat1', {"50":"#c1c5d3","100":"#959bb3","200":"#747d9c","300":"#545b75","400":"#474d63","500":"#3a3f51","600":"#2d313f","700":"#20232d","800":"#14151b","900":"#07080a","A100":"#c1c5d3","A200":"#959bb3","A400":"#474d63","A700":"#20232d",'contrastDefaultColor':'light'});
    $mdThemingProvider.definePalette('Pat2', {"50":"#ffffff","100":"#fbcecf","200":"#f79a9c","300":"#f2585b","400":"#ef3b40","500":"#ed1f24","600":"#dc1217","700":"#bf0f14","800":"#a30d11","900":"#870b0e","A100":"#ffffff","A200":"#fbcecf","A400":"#ef3b40","A700":"#bf0f14",'contrastDefaultColor':'light'});
    $mdThemingProvider.definePalette('Pat3', {"50":"#ffffff","100":"#ffbcbc","200":"#ff8484","300":"#ff3c3c","400":"#ff1e1e","500":"#fe0000","600":"#df0000","700":"#c10000","800":"#a20000","900":"#840000","A100":"#ffffff","A200":"#ffbcbc","A400":"#ff1e1e","A700":"#c10000",'contrastDefaultColor':'light'});
    $mdThemingProvider.definePalette('Pat4', {"50":"#ffffff","100":"#ffffff","200":"#f8f8f8","300":"#d4d4d5","400":"#c5c5c6","500":"#b5b5b7","600":"#a5a5a8","700":"#969699","800":"#86868a","900":"#77777a","A100":"#ffffff","A200":"#ffffff","A400":"#c5c5c6","A700":"#969699",'contrastDefaultColor':'dark'});
    $mdThemingProvider.theme('default')
        .primaryPalette('Pat1')
        .accentPalette('Pat2', {'default': '600'})
        .warnPalette('Pat3')
        .backgroundPalette('Pat4');

    // $mdThemingProvider.definePalette('Pat1', {"50":"#a4a8c4","100":"#757ca6","200":"#596089","300":"#3d425e","400":"#31354c","500":"#252839","600":"#191b26","700":"#0d0e14","800":"#010101","900":"#000000","A100":"#a4a8c4","A200":"#757ca6","A400":"#31354c","A700":"#0d0e14",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#f3f4f4","100":"#caced1","200":"#acb2b7","300":"#858f96","400":"#757f87","500":"#677077","600":"#596167","700":"#4b5156","800":"#3c4246","900":"#2e3235","A100":"#f3f4f4","A200":"#caced1","A400":"#757f87","A700":"#4b5156",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#ffffff","100":"#fdf5e3","200":"#fae2af","300":"#f6ca6c","400":"#f4c04f","500":"#f2b632","600":"#f0ac15","700":"#d9990e","800":"#bc850c","900":"#9f710a","A100":"#ffffff","A200":"#fdf5e3","A400":"#f4c04f","A700":"#d9990e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#ffffff","100":"#ffffff","200":"#f8f8f8","300":"#d4d4d5","400":"#c5c5c6","500":"#b5b5b7","600":"#a5a5a8","700":"#969699","800":"#86868a","900":"#77777a","A100":"#ffffff","A200":"#ffffff","A400":"#c5c5c6","A700":"#969699",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');
    
    // $mdThemingProvider.definePalette('Pat1', {"50":"#fff0f0","100":"#ffa4a5","200":"#ff6c6e","300":"#ff2428","400":"#ff060a","500":"#e60004","600":"#c70003","700":"#a90003","800":"#8a0002","900":"#6c0002","A100":"#fff0f0","A200":"#ffa4a5","A400":"#ff060a","A700":"#a90003",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#fffaf5","100":"#fecfaa","200":"#fdb072","300":"#fc892c","400":"#fc780e","500":"#e86903","600":"#ca5b03","700":"#ac4e02","800":"#8d4002","900":"#6f3201","A100":"#fffaf5","A200":"#fecfaa","A400":"#fc780e","A700":"#ac4e02",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#fffdf3","100":"#fdeea8","200":"#fbe371","300":"#f9d62c","400":"#f9d00e","500":"#e2bc06","600":"#c4a305","700":"#a68a04","800":"#897204","900":"#6b5903","A100":"#fffdf3","A200":"#fdeea8","A400":"#f9d00e","A700":"#a68a04",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#fdfced","100":"#f4efa9","200":"#ede677","300":"#e5db38","400":"#e1d61e","500":"#c6bc1a","600":"#aba216","700":"#908913","800":"#756f0f","900":"#5a550c","A100":"#fdfced","A200":"#f4efa9","A400":"#e1d61e","A700":"#908913",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#fafdf5","100":"#d9f2b4","200":"#c2e984","300":"#a3df48","400":"#96da2e","500":"#86c623","600":"#74ac1e","700":"#63921a","800":"#517815","900":"#405e11","A100":"#fafdf5","A200":"#d9f2b4","A400":"#96da2e","A700":"#63921a",'contrastDefaultColor':'light'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');

    // mangue
    // $mdThemingProvider.definePalette('Pat1', {"50":"#fcf0ee","100":"#f2b7ac","200":"#eb8d7b","300":"#e1583d","400":"#dd4122","500":"#c3391e","600":"#a8311a","700":"#8e2916","800":"#732212","900":"#591a0e","A100":"#fcf0ee","A200":"#f2b7ac","A400":"#dd4122","A700":"#8e2916",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#fefcf9","100":"#f7d7b4","200":"#f1bd82","300":"#ea9b41","400":"#e78c26","500":"#d67d18","600":"#ba6d15","700":"#9f5d12","800":"#834d0f","900":"#683d0c","A100":"#fefcf9","A200":"#f7d7b4","A400":"#e78c26","A700":"#9f5d12",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#b6e5f4","100":"#74ceea","200":"#43bde3","300":"#1d9bc1","400":"#1985a7","500":"#15708C","600":"#115b71","700":"#0d4557","800":"#09303c","900":"#051b22","A100":"#b6e5f4","A200":"#74ceea","A400":"#1985a7","A700":"#0d4557",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#9cb8e1","100":"#618ecf","200":"#3a6fbf","300":"#294f88","400":"#224270","500":"#1B3459","600":"#142641","700":"#0d192a","800":"#060b13","900":"#000000","A100":"#9cb8e1","A200":"#618ecf","A400":"#224270","A700":"#0d192a",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#8cbab1","100":"#5e9c8f","200":"#49796f","300":"#2e4c46","400":"#233935","500":"#172623","600":"#0b1311","700":"#000000","800":"#000000","900":"#000000","A100":"#8cbab1","A200":"#5e9c8f","A400":"#233935","A700":"#000000",'contrastDefaultColor':'light'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');

   
    // green scale
    // $mdThemingProvider.definePalette('Pat1', {"50":"#b0dce3","100":"#77c3cf","200":"#4eb1c1","300":"#348693","400":"#2c727d","500":"#245D66","600":"#1c484f","700":"#143439","800":"#0c1f22","900":"#040a0b","A100":"#b0dce3","A200":"#77c3cf","A400":"#2c727d","A700":"#143439",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#85c7d2","100":"#4daebd","200":"#398c9a","300":"#255d66","400":"#1d484f","500":"#153439","600":"#0d2023","700":"#050b0c","800":"#000000","900":"#000000","A100":"#85c7d2","A200":"#4daebd","A400":"#1d484f","A700":"#050b0c",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#9aded4","100":"#60ccbb","200":"#3bb8a5","300":"#2a8274","400":"#226b60","500":"#1B544B","600":"#143d36","700":"#0c2622","800":"#050f0d","900":"#000000","A100":"#9aded4","A200":"#60ccbb","A400":"#226b60","A700":"#0c2622",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#e0f0e8","100":"#add6c1","200":"#88c4a4","300":"#58ac80","400":"#4c9971","500":"#428562","600":"#387153","700":"#2e5c44","800":"#244835","900":"#193326","A100":"#e0f0e8","A200":"#add6c1","A400":"#4c9971","A700":"#2e5c44",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#fcfefc","100":"#cae5c8","200":"#a5d3a2","300":"#76bd72","400":"#62b35d","500":"#52a44d","600":"#488f43","700":"#3d7a39","800":"#336630","900":"#285126","A100":"#fcfefc","A200":"#cae5c8","A400":"#62b35d","A700":"#3d7a39",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');

    // grey scale
    // $mdThemingProvider.definePalette('Pat1', {"50":"#d4d4d4","100":"#adadad","200":"#919191","300":"#6e6e6e","400":"#5e5e5e","500":"#4F4F4F","600":"#404040","700":"#303030","800":"#212121","900":"#121212","A100":"#d4d4d4","A200":"#adadad","A400":"#5e5e5e","A700":"#303030",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#f6f6f6","100":"#cfcfcf","200":"#b3b3b3","300":"#909090","400":"#808080","500":"#717171","600":"#626262","700":"#525252","800":"#434343","900":"#343434","A100":"#f6f6f6","A200":"#cfcfcf","A400":"#808080","A700":"#525252",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#eff7f8","100":"#bbdae0","200":"#95c6ce","300":"#64abb7","400":"#509fad","500":"#468C98","600":"#3c7983","700":"#33656e","800":"#295259","900":"#1f3f44","A100":"#eff7f8","A200":"#bbdae0","A400":"#509fad","A700":"#33656e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#f3f7f7","100":"#c7d7d7","200":"#a6bfbf","300":"#7da1a1","400":"#6b9595","500":"#5e8383","600":"#517171","700":"#445f5f","800":"#384e4e","900":"#2b3c3c","A100":"#f3f7f7","A200":"#c7d7d7","A400":"#6b9595","A700":"#445f5f",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#f2f5f7","100":"#c1d1db","200":"#9eb7c7","300":"#7096ad","400":"#5d87a1","500":"#52778e","600":"#47677b","700":"#3c5667","800":"#304654","900":"#253640","A100":"#f2f5f7","A200":"#c1d1db","A400":"#5d87a1","A700":"#3c5667",'contrastDefaultColor':'light'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');

    // blue scale 3
    // $mdThemingProvider.definePalette('Pat1', {"50":"#bfe6f2","100":"#80cde5","200":"#52badb","300":"#289abe","400":"#2286a4","500":"#1D718B","600":"#185c72","700":"#124858","800":"#0d333f","900":"#081f26","A100":"#bfe6f2","A200":"#80cde5","A400":"#2286a4","A700":"#124858",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#89d0f8","100":"#41b4f3","200":"#0f9fed","300":"#0b72aa","400":"#095e8d","500":"#074B70","600":"#053853","700":"#032436","800":"#02111a","900":"#000000","A100":"#89d0f8","A200":"#41b4f3","A400":"#095e8d","A700":"#032436",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#8dc1ee","100":"#4b9de4","200":"#2082d6","300":"#175c98","400":"#134c7e","500":"#0F3C63","600":"#0b2c48","700":"#071c2e","800":"#030c13","900":"#000000","A100":"#8dc1ee","A200":"#4b9de4","A400":"#134c7e","A700":"#071c2e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#92b3eb","100":"#5287df","200":"#2767d2","300":"#1b4996","400":"#173d7c","500":"#123062","600":"#0d2348","700":"#09172e","800":"#040a14","900":"#000000","A100":"#92b3eb","A200":"#5287df","A400":"#173d7c","A700":"#09172e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#93b9e6","100":"#5591d8","200":"#2e74c7","300":"#20528d","400":"#1b4374","500":"#15355B","600":"#0f2742","700":"#0a1829","800":"#040a10","900":"#000000","A100":"#93b9e6","A200":"#5591d8","A400":"#1b4374","A700":"#0a1829",'contrastDefaultColor':'light'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');
    
    // brown scale
    // $mdThemingProvider.definePalette('Pat1', {"50":"#fdf9f6","100":"#f1d0b6","200":"#e8b287","300":"#dc8c4b","400":"#d77c31","500":"#c46d26","600":"#aa5f21","700":"#91501c","800":"#774217","900":"#5d3412","A100":"#fdf9f6","A200":"#f1d0b6","A400":"#d77c31","A700":"#91501c",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#f5e9d8","100":"#e6c79b","200":"#dbae6e","300":"#cd8d35","400":"#b67d2d","500":"#9D6C27","600":"#845b21","700":"#6c4a1b","800":"#533915","900":"#3b290f","A100":"#f5e9d8","A200":"#e6c79b","A400":"#b67d2d","A700":"#6c4a1b",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#e1ceb5","100":"#cba97f","200":"#ba8f57","300":"#8f6a3b","400":"#7a5a32","500":"#644A29","600":"#4e3a20","700":"#392a17","800":"#231a0e","900":"#0d0a05","A100":"#e1ceb5","A200":"#cba97f","A400":"#7a5a32","A700":"#392a17",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#ccbab3","100":"#ae9185","200":"#977364","300":"#6c5247","400":"#59443b","500":"#47362F","600":"#352823","700":"#221a17","800":"#100c0a","900":"#000000","A100":"#ccbab3","A200":"#ae9185","A400":"#59443b","A700":"#221a17",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#ff9a39","100":"#ec7300","200":"#b45800","300":"#6c3500","400":"#4e2600","500":"#2F1700","600":"#100800","700":"#000000","800":"#000000","900":"#000000","A100":"#ff9a39","A200":"#ec7300","A400":"#4e2600","A700":"#000000",'contrastDefaultColor':'light'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');
    
    // blue scale
    // $mdThemingProvider.definePalette('Pat1', {"50":"#669bf0","100":"#216de9","200":"#1354be","300":"#0d387e","400":"#0a2b62","500":"#071F46","600":"#04132a","700":"#01060e","800":"#000000","900":"#000000","A100":"#669bf0","A200":"#216de9","A400":"#0a2b62","A700":"#01060e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#bccced","100":"#809ddd","200":"#547bd1","300":"#2f57ae","400":"#284b96","500":"#223F7E","600":"#1b3366","700":"#15274e","800":"#0e1b36","900":"#080f1e","A100":"#bccced","A200":"#809ddd","A400":"#284b96","A700":"#15274e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#eff2f5","100":"#c0cfd7","200":"#9eb4c1","300":"#7293a5","400":"#618498","500":"#557485","600":"#496472","700":"#3d5360","800":"#31434d","900":"#25333a","A100":"#eff2f5","A200":"#c0cfd7","A400":"#618498","A700":"#3d5360",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#7d6ccb","100":"#523dad","200":"#3e2f84","300":"#251c4f","400":"#1b1439","500":"#100C22","600":"#05040b","700":"#000000","800":"#000000","900":"#000000","A100":"#7d6ccb","A200":"#523dad","A400":"#1b1439","A700":"#000000",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#ecf2e4","100":"#c9d9b1","200":"#aec78b","300":"#8daf5b","400":"#7d9f4d","500":"#6D8A43","600":"#5d7539","700":"#4c612f","800":"#3c4c25","900":"#2c381b","A100":"#ecf2e4","A200":"#c9d9b1","A400":"#7d9f4d","A700":"#4c612f",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');

    // blue scale2
    // $mdThemingProvider.definePalette('Pat1', {"50":"#b8bdbd","100":"#909898","200":"#747d7d","300":"#515858","400":"#434848","500":"#343838","600":"#252828","700":"#171818","800":"#080808","900":"#000000","A100":"#b8bdbd","A200":"#909898","A400":"#434848","A700":"#171818",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat2', {"50":"#75f0ff","100":"#29e7ff","200":"#00d5f0","300":"#0095a8","400":"#007a8a","500":"#005F6B","600":"#00444c","700":"#00292e","800":"#000d0f","900":"#000000","A100":"#75f0ff","A200":"#29e7ff","A400":"#007a8a","A700":"#00292e",'contrastDefaultColor':'light'});
    // $mdThemingProvider.definePalette('Pat3', {"50":"#a8f5ff","100":"#5cecff","200":"#24e6ff","300":"#00c2db","400":"#00a7bd","500":"#008C9E","600":"#00717f","700":"#005661","800":"#003b42","900":"#002024","A100":"#a8f5ff","A200":"#5cecff","A400":"#00a7bd","A700":"#005661",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.definePalette('Pat4', {"50":"#d6faff","100":"#8af1ff","200":"#52ebff","300":"#0ae2ff","400":"#00cfeb","500":"#00B4CC","600":"#0099ad","700":"#007e8f","800":"#006370","900":"#004852","A100":"#d6faff","A200":"#8af1ff","A400":"#00cfeb","A700":"#007e8f",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.definePalette('Pat5', {"50":"#ecfdff","100":"#a0f4ff","200":"#68eeff","300":"#20e5ff","400":"#02e2ff","500":"#00c8e2","600":"#00adc3","700":"#0092a5","800":"#007786","900":"#005c68","A100":"#ecfdff","A200":"#a0f4ff","A400":"#02e2ff","A700":"#0092a5",'contrastDefaultColor':'dark'});
    // $mdThemingProvider.theme('default')
    //     .primaryPalette('Pat1')
    //     .accentPalette('Pat2', {'default': '600'})
    //     .warnPalette('Pat3')
    //     .backgroundPalette('Pat4');
  }]);