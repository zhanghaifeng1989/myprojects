import 'package:flutter/material.dart';

class CitySelect extends StatefulWidget {

  @override
  CitySelectState createState() {
    return  new CitySelectState();
  }
}
class CitySelectState extends State<CitySelect>{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('Saved Suggestions'),

      ),
      body: GridView.count(
        primary: false,
        padding: const EdgeInsets.fromLTRB(60, 20, 20, 20),
        crossAxisSpacing: 50.0,
        crossAxisCount: 3,
        children: <Widget>[
          const Text('AAA'),
          const Text('BBB'),
          const Text('CCC'),
          const Text('DDD'),
          const Text('EEE'),
          const Text('FFF'),
        ],
      ),
    );
  }

}