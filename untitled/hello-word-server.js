/**
 * Created by zhanghaifeng on 2018/11/9.
 */
function Student(name,age)
{
    this.name=name;
    this.age=age;
}

var http = require('http');
http.createServer(function handler(req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});

    var tom=new Student("tom",18);
    res.end(JSON.stringify(tom));
    res.end('Hello World\n');
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');