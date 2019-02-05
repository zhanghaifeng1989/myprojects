import 'package:flutter/material.dart';
import 'package:flutter_weather/widget/HomePage.dart';
class MyApp extends StatelessWidget {
@override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new HomePage(mCitys: cityss,),
    );
  }
}
const List<String> cityss = const <String>[
  "上海"
];