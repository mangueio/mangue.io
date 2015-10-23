module.exports = {
	less: {
        files: {
          'websrc/css/app.css': [
            'websrc/css/less/app.less'
          ],
          'websrc/css/md.css': [
            'websrc/css/less/md.less'
          ]
        },
        options: {
          compile: true
        }
    },
    webapp: {
        files: {
            'webapp/css/app.min.css': [
                'websrc/libs/jquery/bootstrap/dist/css/bootstrap.css',
                'websrc/css/md.css',
                'websrc/css/*.css'
            ]
        },
        options: {
            compress: true
        }
    },
    html: {
        files: {
            'html/css/app.min.css': [
                'websrc/libs/jquery/bootstrap/dist/css/bootstrap.css',
                'websrc/css/*.css'
            ]
        },
        options: {
            compress: true
        }
    }
}
