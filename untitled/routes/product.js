var express = require('express');
var router = express.Router();

/* 产品 */
router.get('/', function(req, res, next) {

    var products=[];
    products.push({name:"ZTE U880",price:899.8});
    products.push({name:"HuWei 荣耀8",price:1899.8});
    products.push({name:"iPhone 7 Plus 128G",price:5899.8});

    //将product视图与指定的对象渲染后输出到客户端
    res.render('product', { title: '天狗商城', pdts:products});
});

module.exports = router;