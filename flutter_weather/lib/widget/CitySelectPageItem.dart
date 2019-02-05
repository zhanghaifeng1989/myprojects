import 'package:flutter/material.dart';
import 'package:flutter_weather/widget/CitySelectPage.dart';
class CitySelectItem extends StatefulWidget {
  final String cityname;
  final bool isselected;
  final ItemChangedCallback onItemChanged;
  CitySelectItem({Key key, this.cityname,this.isselected,this.onItemChanged}) : super(key: key);
  @override
  CitySelectItemState createState() {
    return  new CitySelectItemState();
  }
}
class CitySelectItemState extends State<CitySelectItem>{
  @override
  Widget build(BuildContext context) {
      return  new GestureDetector(
        onTap: (){
            widget.onItemChanged(widget.cityname);
        },
        child: new Container(
          alignment:Alignment.topRight,
          child: new Stack(
            alignment: Alignment.bottomCenter,
            children: <Widget>[
              new Align(
                alignment:FractionalOffset.center,
                child:  Text(widget.cityname, style: TextStyle(color: Colors.blue),),
              ),

              new Container(
                child: Align(
                  alignment: Alignment.topRight,
                  child: widget.isselected?ImageIcon(AssetImage('assets/Select.png'), size:20.0, color: Colors.blue,):null,
                ),
              ),
            ],
          ),
          decoration: new BoxDecoration(
            border: new Border.all(width: 1.0, color: Colors.blue),
            color: Colors.white,
          ),
        ),
      );
  }




}

