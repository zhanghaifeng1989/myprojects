/**
 * Created by zhanghaifeng on 2018/11/9.
 */
//导入lodash模块
var _= require('lodash');

var products=[];
products.push({name:"ZTE U880",price:899.8});
products.push({name:"HuWei 荣耀8",price:1899.8});
products.push({name:"iPhone 7 Plus 128G",price:5899.8});

//1、取出第一个元素
var obj1=_.first(products);
console.log(1111,obj1.name);  //ZTE U880

//2、取出最后一个元素
var obj2=_.last(products);
console.log(2222,obj2.name);  //iPhone 7 Plus 128G

//3、指定查找条件返回符合条件的索引
var obj3=_.findIndex(products,function(obj){
    return obj.price>=1000&&obj.name.indexOf("7")>0;
});
console.log(3333,obj3);  //2

//4、指定查找条件返回查找到的对象
var obj4=_.find(products,function(obj){
    return obj.price>=1000&&obj.name.indexOf("7")>0;
});
console.log(4444,obj4);  //{ name: 'iPhone 7 Plus 128G', price: 5899.8 }

//5、排序
var obj5=_.orderBy(products,["price","name"],["desc","asc"]);
console.log(5555,obj5);

//[ { name: 'iPhone 7 Plus 128G', price: 5899.8 },
//{ name: 'HuWei 荣耀8', price: 1899.8 },
//{ name: 'ZTE U880', price: 899.8 } ]

//6、查找价格为1899.8的产品的key
var obj6=_.findKey(products,{price:1899.8});
console.log(6666,obj6);   //1