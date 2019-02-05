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
class CityCardState extends State<CityCard> with AutomaticKeepAliveClientMixin{
  var result = '';
  bool hideprogress = true;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    print("initState"+widget.cityname);

  }

 @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
  @override
  Widget build(BuildContext context) {
    if(result.isEmpty) {
      getWeather();
    }
    print(widget.cityname);
    final TextStyle textStyle = Theme.of(context).textTheme.display1;
    return new Scaffold(
      backgroundColor: Colors.black,
      body: new Center(
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
            new MaterialButton(
              onPressed: (){

              },
              child: new Text(widget.cityname),
              color: Colors.blue,
              height: 10,
            )
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



