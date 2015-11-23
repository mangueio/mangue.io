module.exports = {
	less: {
        files: {
          'webapp/css/app.css': [
            'webapp/css/less/app.less'
          ],
          'webapp/css/md.css': [
            'webapp/css/less/md.less'
          ]
        },
        options: {
          compile: true
        }
    },
    webapp: {
        files: {
            'webapp/css/app.min.css': [
                'webapp/libs/jquery/bootstrap/dist/css/bootstrap.css',
                'webapp/css/md.css',
                'webapp/css/*.css'
            ]
        },
        options: {
            compress: true
        }
    },
    html: {
        files: {
            'html/css/app.min.css': [
                'webapp/libs/jquery/bootstrap/dist/css/bootstrap.css',
                'webapp/css/*.css'
            ]
        },
        options: {
            compress: true
        }
    }
}
