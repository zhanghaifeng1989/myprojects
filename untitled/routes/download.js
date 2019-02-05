var express = require('express');
var url = require('url');
var fs = require('fs');
var http = require('http');
var queryString = require('querystring');
var bodyParser = require('body-parser');
var path=require('path');


var router = express.Router();


router.post('/down',function(request,res,next){
    console.log('下载请求 '+ (new Date()));
    var filePath = req.body.filePath;
    console.log('download');
    var responseBody = {};
    if(fs.existsSync(filePath)){
        var data = fs.readFileSync(filePath);
        var dataBase64 = data.toString('base64');
        responseBody = JSON.stringify({
            returnMsg:'200',
            filePath:filePath,
            fileData:dataBase64
        });
        console.log('下载完成 '+ (new Date()));
    }else{
        responseBody=JSON.stringify({
            returnMsg:'400'
        });
        console.log('文件未找到 '+ (new Date()));
    }
    //console.log('*** ' + ++count +' ***');
    resp.status(200).end(responseBody.toString());

});
module.exports = router;
