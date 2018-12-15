var express = require('express');
var router = express.Router();
// router.get('/', function(req, res, next) {
//      res.render('index', { title: 'Express' });
// });
/* GET home page. */
//http://localhost/88?id=8
router.get('/', function(req, res, next) {
     res.send(req.query);
});

module.exports = router;
