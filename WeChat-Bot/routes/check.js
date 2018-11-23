var express = require('express');
var crypto = require('crypto');
var router = express.Router();
var xmlParse=require('xml2js').parseString;

var token = "zhanghaifeng"; //此处需要你自己修改！

/* GET home page. */
router.get('/', function(req, res, next) {
   console.log("进来了");
    var signature = req.query.signature;
    var timestamp = req.query.timestamp;
    var nonce = req.query.nonce;
    var echostr = req.query.echostr;

    console.log("signature==",signature);
    console.log("timestamp==",timestamp);
    console.log("nonce==",nonce);
    console.log("echostr==",echostr);


    /*  加密/校验流程如下： */
    //1. 将token、timestamp、nonce三个参数进行字典序排序
    var array = new Array(token,timestamp,nonce);
    array.sort();
    var str = array.toString().replace(/,/g,"");

    //2. 将三个参数字符串拼接成一个字符串进行sha1加密
    var sha1Code = crypto.createHash("sha1");
    var code = sha1Code.update(str,'utf-8').digest("hex");

    //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
    if(code===signature){
        res.send(echostr)
    }else{
        res.send("error");
    }
});
router.post('/',function(req,res,next) {
    console.log("post进来了");
    var data = '';

    req.on('data', (chunk) => {
        data += chunk;
    });
    req.on('end', () => {
        xmlParse(data, (err, result) => {
            // if(result.xml.MsgType=='event'){
            //     if(result.xml.Event=='subscribe'){
            //         common.dealText('欢迎关注聊天小喵;\n 1.发送地址定位信息可以获得当地天气情况哟\n 2.发送歌曲名字可以获取想要听的歌哟\n3.发送语音信息可以直接小喵聊天哟，她会很多东西哟\n图片,视频识别功能还在开发中\n',res,result);
            //     }else if(result.xml.Event=='CLICK' && result.xml.EventKey=='V1001_IT_NEWS'){
            //         common.requestMsg('https://api.tianapi.com/it/?key=APIkey&num=2',result,res);//API
            //         key是天行数据的新闻apikey
            //     }else if(result.xml.Event=='CLICK' && result.xml.EventKey=='V1001_TRAVEL_NEWS'){
            //         common.requestMsg('https://api.tianapi.com/travel/?key=APIkey&num=2',result,res);//如上
            //     }else if(result.xml.Event=='CLICK' && result.xml.EventKey=='V1001_VR_NEWS'){
            //         common.requestMsg('https://api.tianapi.com/vr/?key=APIkey&num=2',result,res);//如上
            //     }else if(result.xml.Event=='CLICK' && result.xml.EventKey=='V1001_AMUSE_NEWS'){
            //         common.requestMsg('https://api.tianapi.com/huabian/?key=APIkey&num=2',result,res);
            //     }
            // }
            // if(result.xml.MsgType=='location'){
            //     let lat=(result.xml.Location_X);
            //     let log=(result.xml.Location_Y);
            //     const url='http://api.yytianqi.com/forecast7d?city='+lat+','+log+'&key=APIKEY';
            //     common.requestWeather(url,result,res);
            // }
            // if(result.xml.MsgType=='image'){
            //     let str= '<xml><ToUserName><![CDATA['+result.xml.FromUserName+']]></ToUserName><FromUserName><![CDATA['+result.xml.ToUserName+']]></FromUserName><CreateTime>'+new Date().getTime()+'</CreateTime><MsgType><![CDATA['+'image'+']]></MsgType><Image><MediaId><![CDATA['+result.xml.MediaId+']]></MediaId></Image></xml>';
            //     res.send(str);
            // }
            // if(result.xml.MsgType=='text'){
            //     let responseMSg=(result.xml.Content).toString();
            //     let url=encodeURI("http://s.music.163.com/search/get?type=1&limit=10&offset=0&s="+responseMSg);
            //     common.requestSong(url,result,res);
            // }
            // if(result.xml.MsgType=='voice'){
            //     let url='http://www.tuling123.com/openapi/api?key=APIKEY&info='+encodeURI(result.xml.Recognition.toString());
            //     common.requestRobot(url,result,res);
            // }
            //
            // if(result.xml.MsgType=='video'){
            //     let str='<xml><ToUserName><![CDATA['+result.xml.FromUserName+']]></ToUserName><FromUserName><![CDATA['+result.xml.ToUserName+']]></FromUserName><CreateTime>'+new Date().getTime()+'</CreateTime><MsgType><![CDATA['+'video'+']]></MsgType><Video><MediaId><![CDATA['+result.xml.MediaId+']]></MediaId><Title><![CDATA['+'video_info'+']]></Title><Description><![CDATA['+'information'+']]></Description></Video></xml>'
            //     res.send(str);
            // }
            let str='<xml><ToUserName><![CDATA['+result.xml.FromUserName+']]></ToUserName><FromUserName><![CDATA['+result.xml.ToUserName+']]></FromUserName><CreateTime>'+new Date().getTime()+'</CreateTime><MsgType><![CDATA['+'text'+']]></MsgType><Content><![CDATA[http://zhf1989.free.idcfengye.com]]></Content></xml>';

            console.log("send====", str);
            res.send(str);

            console.log("result====", result);
        });


        // res.render('test');
    });
});


module.exports = router;