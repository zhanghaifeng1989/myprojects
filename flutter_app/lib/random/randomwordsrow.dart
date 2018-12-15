import 'package:flutter/material.dart';
import 'package:flutter_app/random/Todo.dart';
import 'package:flutter_app/mystyle.dart';
import 'package:flutter_app/random/randomwordslist.dart';
class RandomWordsItem extends StatelessWidget {
  final Todo mtodo;
  final bool alreadySaved;
  final CartChangedCallback onCartChanged;

  RandomWordsItem({Key key,  this.mtodo,this.alreadySaved,this.onCartChanged}) : super(key: key);
//  RandomWordsItem({Key key,  this.alreadySaved}) : super(key: key);
//  ShoppingListItem({Product product, this.inCart, this.onCartChanged})
//      : product = product,
//        super(key: new ObjectKey(product));
  @override
  Widget build(BuildContext context) {
//  final bool alreadySaved = _saved.contains(pair);
  return new ListTile(
    title: new Text(
      mtodo.title,
      style: MyStyle.mybiggerFont,
    ),
    trailing: new Icon(
      alreadySaved ? Icons.favorite : Icons.favorite_border,
      color: alreadySaved ? Colors.red : null,
    ),
    onTap: () {
//      setState(() {
//        if (alreadySaved) {
//          _saved.remove(pair);
//        } else {
//          _saved.add(pair);
//        }
//        print("onTap=="+_saved.length.toString());
//      });
    if(!alreadySaved){
      print("onTap==选中");
    }else{
      print("onTap==取消");

    }
      onCartChanged(mtodo,!alreadySaved);
    },
  );
  }
}