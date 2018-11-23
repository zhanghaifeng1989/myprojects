var express = require('express');
var router = express.Router();
var request = require('request');
var data = require('global');
var wechat = require('./wechatutils');



router.get('/gettoken', function(req, res, next) {
    wechat.jobGetAccessToken();
    res.send('gettoken');
});
router.get('/getip', function(req, res, next) {
    wechat.getip();
    res.send('getip');
});
router.get('/checknet', function(req, res, next) {
    wechat.checknet();
    res.send('checknet');
});
router.get('/createmenu', function(req, res, next) {
    wechat.createmenu();
    res.send('createmenu');
});


// function jobGetAccessToken() {
//     var period = 7200000; // 7200 second
//     var opts = {
//         method: 'GET',
//         url: 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx39df52df96b9fa79&secret=5297cc49264598d9198d6fab71732926',
//         header: {
//         'content-type': 'application/json' // 默认值
//     }
// }
// // 获取access_token接口
// get(opts).then(function (res) {
//     code = JSON.parse(res);
//     if (code.access_token != null){
//          data.accessToken = code.access_token;
//          console.log('accessToken:' + code.access_token);}
//     else {
//         console.log('job error:' + res);
//     }
// }).catch(function (err) {
//     console.log(err);
// })
// //定时任务
// setInterval(function () {
//     get(opts).then(function (res) {
//         code = JSON.parse(res);
//         if (code.access_token != null)
//             data.accessToken = code.access_token;
//         else {
//             console.log('job error:' + res);
//         }
//     }).catch(function (err) {
//         console.log(err);
//     })
// }, period);
// }
//
// /**
//  * Http get请求
//  * 参数：opts封装url等字典
//  */
// function get(opts) {
//     return new Promise((resolve, reject) => {
//         request.get(opts, function (err, response, body) {
//             if (!err && response.statusCode == 200) {
//                 if (body !== 'null') {
//                     results = body;
//                     resolve(results);
//                 } else {
//                     resolve('');
//                 }
//             } else {
//                 reject(err);
//             }
//         });
//     });
// }












module.exports = router;