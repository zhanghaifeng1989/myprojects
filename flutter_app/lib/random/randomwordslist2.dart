import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_app/mystyle.dart';
import 'package:flutter_app/random/Todo.dart';


class RandomWordsSelect extends StatelessWidget  {
  final  Set<Todo> saved ;
  RandomWordsSelect({Key key,  this.saved}) : super(key: key);
  final _biggerFont = MyStyle.mybiggerFont;
 @override
  Widget build(BuildContext context) {
   final tiles = saved.map(
         (pair) {
       return new ListTile(
         title: new Text(
           pair.title,
           style: _biggerFont,
         ),
       );
     },
   );
   final divided = ListTile
       .divideTiles(
     context: context,
     tiles: tiles,
   )
       .toList();

   return new Scaffold(
     appBar: new AppBar(
       title: new Text('Saved Suggestions'),
     ),
     body: new ListView(children: divided),
   );
  }
}