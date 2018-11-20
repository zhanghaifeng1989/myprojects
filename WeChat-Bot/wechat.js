var wechat = require('wechat');
var express = require('express');

var config = {
    token: 'zhanghaifeng',//token是你申请测试公众号时候填写的token
    appid: 'wx39df52df96b9fa79',//appid是申请时，自动生成的，就在最顶部
    encodingAESKey: 'xKwfvrzm8KAhtsssRmnQF3K9Z2a0iY06psved1FZOmb',
    checkSignature: true // 可选，默认为true。由于微信公众平台接口调试工具在明文模式下不发送签名，所以如要使用该测试工具，请将其设置为false
};
var app = express();

//以下开始为获取到微信服务器发送过来的消息，并在此处回复消息
//此处监控的是URL的wechat，那么在配置微信的URL时，也需要在主机URL地址后面加入wechat这样才可以获取到数据
app.use(express.query());
app.use('/wechat', wechat(config, function (req, res, next) {
    // 微信输入信息都在req.weixin上
    var message = req.weixin;


    console.log(message);
    if(message.MsgType === 'text')
    {
        res.reply('这是一个自动回复');
    }
    else if(message.MsgType === 'voice')
    {
    }
    else if(message.MsgType === 'image')
    {
        res.reply([
            {
                title: '文章提示',
                description: '返回的是文章',
                picurl: 'http://pic002.cnblogs.com/images/2011/159097/2011102917303125.jpg',
                url: 'http://doxmate.cool/node-webot/wechat/api.html'
            }
        ]);
    }
}));