

var request = require('request');
var data = require('global');

exports.jobGetAccessToken = function(){
var period = 7200000; // 7200 second
    var opts = {
        method: 'GET',
        url: 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx39df52df96b9fa79&secret=5297cc49264598d9198d6fab71732926',
        header: {
            'content-type': 'application/json' // 默认值
        }
    }
// 获取access_token接口
    get(opts).then(function (res) {
        code = JSON.parse(res);
        if (code.access_token != null){
            data.accessToken = code.access_token;
            console.log('accessToken:' + code.access_token);}
        else {
            console.log('job error:' + res);
        }
    }).catch(function (err) {
        console.log(err);
    })
//定时任务
    setInterval(function () {
        get(opts).then(function (res) {
            code = JSON.parse(res);
            if (code.access_token != null)
                data.accessToken = code.access_token;
            else {
                console.log('job error:' + res);
            }
        }).catch(function (err) {
            console.log(err);
        })
    }, period);
}


exports.getip = function () {
    var opts = {
        method: 'GET',
        url: 'https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token='+data.accessToken,
        header: {
            'content-type': 'application/json' // 默认值
        }
    }
// 获取access_token接口
    get(opts).then(function (res) {
        code = JSON.parse(res);
        console.log(code);
    }).catch(function (err) {
        console.log(err);
    })
}

exports.checknet = function () {
    var opts = {
        url: 'https://api.weixin.qq.com/cgi-bin/callback/check?access_token=' + data.accessToken,
        method: "POST",
        json: {
            "action": "all",
            "check_operator": "DEFAULT"
        },
        header: {
            "content-type": "application/json",
        }
    }
    var body = {
        "action": "all",
        "check_operator": "DEFAULT"
    };
    post(opts).then(function (res) {
        // code = JSON.parse(res);
        console.log(res);
    }).catch(function (err) {
        console.log(err);
    })
}



/**
 * Http get请求
 * 参数：opts封装url等字典
 */
function get(opts) {
    return new Promise((resolve, reject) => {
        request(opts, function (err, response, body) {
            if (!err && response.statusCode == 200) {
                if (body !== 'null') {
                    results = body;
                    resolve(results);
                } else {
                    resolve('');
                }
            } else {
                reject(err);
            }
        });
    });
}


function post(opts) {
    return new Promise((resolve, reject) => {
        console.log("opts===",opts);

        request(opts, function (err, response, body) {
            if (!err && response.statusCode == 200) {
                if (body !== 'null') {
                    results = body;
                    resolve(results);
                } else {
                    resolve('');
                }
            } else {
                reject(err);
            }
        });
    });
}
