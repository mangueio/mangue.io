module.exports = {
	min: {
      files: [{
          expand: true,
          cwd: 'webapp/tpl/',
          src: ['*.html', '**/*.html'],
          dest: 'webapp/tpl/',
          ext: '.html',
          extDot: 'first'
      }]
  }
}