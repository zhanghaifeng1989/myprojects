import 'dart:io';
import 'dart:convert';
import 'package:flutter_app/random/Response.dart';
import 'package:flutter_app/main.dart';
import 'package:flutter_app/random/WeatherResponseBody.dart';
void getWeatherHttp(OnWeatherCallback callback,String cityname)  async{
  String key = '60fdc912d0421ab9d87208f21ad51a4d';
  ResponseBody resbody;
  var url = 'http://v.juhe.cn/weather/index?cityname='+cityname+'&dtype=&format=&key='+key;
  var httpClient = new HttpClient();
  try {
    var request = await httpClient.getUrl(Uri.parse(url));
    var response = await request.close();
    if (response.statusCode == HttpStatus.ok) {
      var json = await response.transform(utf8.decoder).join();
      Map resmap = jsonDecode(json);
      resbody  = new ResponseBody.fromJson(resmap);
      print("json==="+json.toString());
      print("resbody==="+resbody.toJson().toString());

    } else {
      print("erro");

    }
  } catch (exception) {
    print("exception");

  }
  callback(resbody);

}






