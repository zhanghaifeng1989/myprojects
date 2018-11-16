var express = require('express');
var router = express.Router();

/* GET users listing. */
// router.get('/', function(req, res, next) {
//   res.send('respond with a ');
// });

// //获得产品根据Id
// router.get('/:id/:category',function(request,res,next){
//     res.send(request.params.id+","+request.params.category);
// });

//http://localhost:3001/users/123?name=111
router.get('/:id',function(request,res,next){
    res.send("name:"+request.query.name);
});

module.exports = router;
