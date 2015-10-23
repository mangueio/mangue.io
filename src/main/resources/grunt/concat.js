module.exports = {
  webapp:{
    src:[
      'websrc/libs/jquery/jquery/dist/jquery.js',

      'websrc/libs/angular/angular/angular.js',
      
      'websrc/libs/angular/angular-animate/angular-animate.js',
      'websrc/libs/angular/angular-aria/angular-aria.js',
      'websrc/libs/angular/angular-cookies/angular-cookies.js',
      'websrc/libs/angular/angular-messages/angular-messages.js',
      'websrc/libs/angular/angular-resource/angular-resource.js',
      'websrc/libs/angular/angular-sanitize/angular-sanitize.js',
      'websrc/libs/angular/angular-touch/angular-touch.js',
      'websrc/libs/angular/angular-material/angular-material.js',

      'websrc/libs/angular/angular-ui-router/release/angular-ui-router.js', 
      'websrc/libs/angular/ngstorage/ngStorage.js',
      'websrc/libs/angular/angular-ui-utils/ui-utils.js',

      'websrc/libs/angular/angular-bootstrap/ui-bootstrap-tpls.js',
     
      'websrc/libs/angular/oclazyload/dist/ocLazyLoad.js',
     
      'websrc/libs/angular/angular-translate/angular-translate.js',
      'websrc/libs/angular/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
      'websrc/libs/angular/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      'websrc/libs/angular/angular-translate-storage-local/angular-translate-storage-local.js',

      'websrc/js/*.js',
      'websrc/js/directives/*.js',
      'websrc/js/services/*.js',
      'websrc/js/filters/*.js',
      'websrc/js/controllers/bootstrap.js'
    ],
    dest:'webapp/js/app.src.js'
  },
  html:{
    src:[
      'websrc/libs/jquery/jquery/dist/jquery.js',
      'websrc/libs/jquery/bootstrap/dist/js/bootstrap.js',
      'html/js/*.js'
    ],
    dest:'html/js/app.src.js'
  }
}
