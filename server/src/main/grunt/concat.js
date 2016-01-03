module.exports = {
  webapp:{
    src:[
      'webapp/libs/jquery/jquery/dist/jquery.js',

      'webapp/libs/angular/angular/angular.js',
      
      'webapp/libs/angular/angular-animate/angular-animate.js',
      'webapp/libs/angular/angular-aria/angular-aria.js',
      'webapp/libs/angular/angular-cookies/angular-cookies.js',
      'webapp/libs/angular/angular-messages/angular-messages.js',
      'webapp/libs/angular/angular-resource/angular-resource.js',
      'webapp/libs/angular/angular-sanitize/angular-sanitize.js',
      'webapp/libs/angular/angular-touch/angular-touch.js',
      'webapp/libs/angular/angular-material/angular-material.js',

      'webapp/libs/angular/angular-ui-router/release/angular-ui-router.js', 
      'webapp/libs/angular/ngstorage/ngStorage.js',
      'webapp/libs/angular/angular-ui-utils/ui-utils.js',

      'webapp/libs/angular/angular-bootstrap/ui-bootstrap-tpls.js',
     
      'webapp/libs/angular/oclazyload/dist/ocLazyLoad.js',
     
      'webapp/libs/angular/angular-translate/angular-translate.js',
      'webapp/libs/angular/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
      'webapp/libs/angular/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      'webapp/libs/angular/angular-translate-storage-local/angular-translate-storage-local.js',

      'webapp/js/*.js',
      'webapp/js/directives/*.js',
      'webapp/js/services/*.js',
      'webapp/js/filters/*.js',
      'webapp/js/controllers/bootstrap.js'
    ],
    dest:'webapp/js/app.src.js'
  },
  html:{
    src:[
      'webapp/libs/jquery/jquery/dist/jquery.js',
      'webapp/libs/jquery/bootstrap/dist/js/bootstrap.js',
      'html/js/*.js'
    ],
    dest:'html/js/app.src.js'
  }
}
