var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) {
    res.render('upload', { title: '上传apk'});

});
module.exports = router;
