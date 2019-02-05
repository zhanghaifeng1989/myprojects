import 'package:flutter/material.dart';
import 'package:flutter_weather/widget/HomePage.dart';
import 'package:flutter_weather/widget/CitySelectPageItem.dart';
class CitySelect extends StatefulWidget {
   List<String> selectCitys;
  CitySelect({Key key, this.selectCitys}) : super(key: key);
  @override
  CitySelectState createState() {
    return  new CitySelectState();
  }
}

typedef void ItemChangedCallback(String cityname);



class CitySelectState extends State<CitySelect>{
  List<String> mSelectCitys = new List<String>();


  @override
  void initState() {
    super.initState();
    mSelectCitys.addAll(widget.selectCitys);
  }

  @override
  Widget build(BuildContext context) {
    print("CitySelect_build");
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('添加城市',style:new TextStyle(color: Colors.white),),
        leading: new IconButton(
          tooltip: 'SelectCity',
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
//            Navigator.of(context).push(
//              new MaterialPageRoute(
//                builder: (context) {
//                  return new HomePage();
//                },
//              ),
//            );
            },
        ),
      ),
      body: GridView.count(
        primary: false,
        padding: const EdgeInsets.fromLTRB(40, 20, 20, 20),
        crossAxisSpacing: 50.0,
        mainAxisSpacing: 20,
        crossAxisCount: 3,
        childAspectRatio: 5/2,
        children:buildGridTileList(),
      ),
    );
  }


  List<Widget> buildGridTileList() {
    print("CitySelect_buildGridTileList");
    List<Widget> widgetList = new List();
    for (int i = 0; i < allcitys.length; i++) {
      widgetList.add(new CitySelectItem(cityname: allcitys[i],isselected: mSelectCitys.contains(allcitys[i]),onItemChanged: _handleItemChanged,));
    }
    return widgetList;
  }


  void returnMain(String text){
    Navigator.pop(context,text);
  }

  void _handleItemChanged(String cityname) {
    setState(() {
      if (mSelectCitys.contains(cityname)) {
        if(mSelectCitys.length==1){
          showDialog(
              context: context,
              builder: (_) => new AlertDialog(
                  title: new Text("提示"),
                  content: new Text("选择城市不能少于1个"),
                  actions:<Widget>[
                    new FlatButton(child:new Text("OK"), onPressed: (){
                      Navigator.of(context).pop();

                    },)
                  ]

              ));
        }else {
          mSelectCitys.remove(cityname);
          Navigator.of(context).push(
              new MaterialPageRoute(
                builder: (context) {
                  return new HomePage(mCitys: mSelectCitys,);
                },
              ),
            );
        }
      } else {
        mSelectCitys.add(cityname);
        Navigator.of(context).push(
              new MaterialPageRoute(
                builder: (context) {
                  return new HomePage(mCitys: mSelectCitys,);
                },
              ),
            );
      }
    });
  }
}

const List<String> allcitys = const <String>[
  '上海',
  '北京',
  '广州',
  '深圳',
'天津',
'重庆',
'南京',
'杭州',
'苏州',
'武汉',
'成都',
'西安',
'哈尔滨',
'厦门',
'呼和浩特',
'南昌',
'贵阳',
'济南',
'合肥',
'昆明',
'南通',
'无锡',
'常州',
];


