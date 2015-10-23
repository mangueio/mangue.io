module.exports = {
	min: {
      files: [{
          expand: true,
          cwd: 'websrc/tpl/',
          src: ['*.html', '**/*.html'],
          dest: 'webapp/tpl/',
          ext: '.html',
          extDot: 'first'
      }]
  }
}