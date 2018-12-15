import 'package:flutter/material.dart';
import 'package:flutter_weather/widget/HomePageItem.dart';
import 'package:flutter_weather/widget/CitySelectPage.dart';
class HomePage extends StatefulWidget{
@override
  State<StatefulWidget> createState() {
    return new HomePageState();
  }
}
class HomePageState extends State<HomePage>with SingleTickerProviderStateMixin{
  TabController _tabController;
  List<String> citys = const <String>[
   '苏州','上海'
  ];
  @override
  void initState() {
    super.initState();
    _tabController = new TabController(vsync: this, length: citys.length);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  void _nextPage(int delta) {
    final int newIndex = _tabController.index + delta;
    if (newIndex < 0 || newIndex >= _tabController.length)
      return;
    _tabController.animateTo(newIndex);
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          backgroundColor:Colors.white54,
          title: new Text('AppBar Bottom Widget',style:new TextStyle(color: Colors.black26),),
          leading: new IconButton(
            tooltip: 'Previous choice',
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Navigator.of(context).push(
              new MaterialPageRoute(
                builder: (context) {
                  return new CitySelect();
                },
              ),
            );},
          ),
          actions: <Widget>[
            new IconButton(
              icon: const Icon(Icons.arrow_forward),
              tooltip: 'Next choice',
              onPressed: () { _nextPage(1); },
            ),
          ],
          bottom: new PreferredSize(
            preferredSize: const Size.fromHeight(48.0),
            child: new Theme(
              data: Theme.of(context).copyWith(accentColor: Colors.white),
              child: new Container(
                height: 48.0,
                alignment: Alignment.center,
                child: new TabPageSelector(controller: _tabController),
              ),
            ),
          ),
        ),
        body: new TabBarView(
          controller: _tabController,
          children: citys.map((String city) {
            return new Padding(
              padding: const EdgeInsets.all(16.0),
              child: new CityCard(cityname: city),
            );
          }).toList(),
        ),
      ),
    );
  }
}







