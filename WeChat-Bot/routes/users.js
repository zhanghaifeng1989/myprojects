var express = require('express');
var router = express.Router();
var data = require('global');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send(data.accessToken);
});

module.exports = router;
