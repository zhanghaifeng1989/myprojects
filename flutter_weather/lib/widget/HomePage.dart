import 'package:flutter/material.dart';
import 'package:flutter_weather/widget/HomePageItem.dart';
import 'package:flutter_weather/widget/CitySelectPage.dart';
class HomePage extends StatefulWidget{
  final List<String> mCitys;
  HomePage({Key key, this.mCitys}) : super(key: key);
  @override
  State<StatefulWidget> createState() {
    return new HomePageState();
  }
}
class HomePageState extends State<HomePage>with SingleTickerProviderStateMixin{
  TabController _tabController;
  @override
  void initState() {
    super.initState();
    _tabController = new TabController(vsync: this, length: widget.mCitys.length);
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
        backgroundColor: Colors.white,
        appBar: new AppBar(
          title: new Text('天气预报',style:new TextStyle(color: Colors.white),),
          leading: new IconButton(
            tooltip: 'SelectCity',
            icon: const Icon(Icons.add),
            onPressed: () {
//              setState(() {
//                citys.add("广州");
//              });

              Navigator.of(context).push(
              new MaterialPageRoute(
                builder: (context) {
                  return new CitySelect(selectCitys: widget.mCitys,);
                },
              ),
            );
              },
          ),

          bottom: widget.mCitys.length==1?null:new PreferredSize(
              preferredSize: const Size.fromHeight(2>1?28.0:0),
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
//         body: new Center(
//           child: new Column(
//             mainAxisSize: MainAxisSize.min,
//             crossAxisAlignment: CrossAxisAlignment.center,
//               children: citys.map((String city) {
//
//            return      new MaterialButton(
//              onPressed: (){
//
//              },
//              child: new Text(city),
//              color: Colors.blue,
//              height: 10,
//            );
//
//          }).toList(),
//           ),
//
//         ),
        body: new TabBarView(
          controller: _tabController,
          children: widget.mCitys.map((String city) {
//            return new Padding(
//              padding: const EdgeInsets.all(0.0),
//              child: new CityCard(cityname: city),
//            );
            return new CityCard(cityname: city);

          }).toList(),
        ),
      ),
    );
  }
}
const List<String> cityss = const <String>[
  "上海","北京"
];







