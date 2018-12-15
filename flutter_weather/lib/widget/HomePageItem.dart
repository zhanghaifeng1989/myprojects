import 'package:flutter/material.dart';
import 'package:flutter_weather/network/HttpManager.dart';
import 'package:flutter_weather/entity/Response.dart';
class CityCard extends StatefulWidget {
  const CityCard({ Key key, this.cityname}) : super(key: key);
  final String cityname;

  @override
  CityCardState createState() {
    return  new CityCardState();
  }
}
class CityCardState extends State<CityCard>{
  var result = '';
  bool hideprogress = true;
  @override
  Widget build(BuildContext context) {
    if(result.isEmpty) {
      getWeather();
    }
    final TextStyle textStyle = Theme.of(context).textTheme.display1;
    return new Card(
      color: Colors.white,
      child: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new Text('$result'),
            new Offstage(
              offstage: hideprogress,
              child: new CircularProgressIndicator(valueColor: AlwaysStoppedAnimation(Colors.black26)),
            ),
            new RaisedButton(
              onPressed: getWeather,
              child: new Text('Get'),
            ),
          ],
        ),
      ),
    );
  }
getWeather(){
  setState(() {
    hideprogress = false;
  });
  getWeatherHttp(handlerWeatherCallback,widget.cityname);
}
handlerWeatherCallback(ResponseBody resp){
  String res = resp.toJson().toString();
  setState(() {
    result = res;
    hideprogress = true;
  });
}
}



