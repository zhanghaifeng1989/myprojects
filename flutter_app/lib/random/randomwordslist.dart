import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_app/mystyle.dart';
import 'package:flutter_app/random/randomwordslist2.dart';
import 'package:flutter_app/random/Todo.dart';
import 'package:flutter_app/random/randomwordsrow.dart';

class RandomWords extends StatefulWidget {
  final  List<Todo> suggestions ;
  RandomWords({Key key,  this.suggestions}) : super(key: key);
  @override
  createState() => new RandomWordsState();
}
typedef void CartChangedCallback(Todo todo, bool isselect);


class RandomWordsState extends State<RandomWords> {
  var _biggerFont = MyStyle.mybiggerFont;
     final _saved = new Set<Todo>();

  @override
  Widget build(BuildContext context) {
    return new Scaffold (
      appBar: new AppBar(
        title: new Text('Startup Name'),
        actions: <Widget>[
          new IconButton(icon: new Icon(Icons.list), onPressed: _pushSaved),
        ],
      ),
      body: _buildSuggestions(widget.suggestions),
    );
  }

  Widget _buildSuggestions( List<Todo> _suggestions) {


    return new ListView.builder(
        itemCount: _suggestions.length,
        padding: const EdgeInsets.all(16.0),
        // 对于每个建议的单词对都会调用一次itemBuilder，然后将单词对添加到ListTile行中
        // 在偶数行，该函数会为单词对添加一个ListTile row.
        // 在奇数行，该行书湖添加一个分割线widget，来分隔相邻的词对。
        // 注意，在小屏幕上，分割线看起来可能比较吃力。
        itemBuilder: (context, i) {
          // 在每一列之前，添加一个1像素高的分隔线widget
//          if (i.isOdd) return new Divider();

//          // 语法 "i ~/ 2" 表示i除以2，但返回值是整形（向下取整），比如i为：1, 2, 3, 4, 5
//          // 时，结果为0, 1, 1, 2, 2， 这可以计算出ListView中减去分隔线后的实际单词对数量
//          final index = i ~/ 2;
//          // 如果是建议列表中最后一个单词对
//          if (index >= _suggestions.length) {
//            // ...接着再生成10个单词对，然后添加到建议列表
//            _suggestions.addAll(generateWordPairs().take(10));
//          }
//          return _buildRow(_suggestions[index]);
            return new RandomWordsItem(mtodo:_suggestions[i] ,alreadySaved: _saved.contains(_suggestions[i]),onCartChanged: _handleCartChanged,);
        }
    );
  }

  Widget _buildRow(Todo pair) {
    final bool alreadySaved = _saved.contains(pair);

    return new ListTile(
      title: new Text(
        pair.title,
        style: _biggerFont,
      ),
      trailing: new Icon(
        alreadySaved ? Icons.favorite : Icons.favorite_border,
        color: alreadySaved ? Colors.red : null,
      ),
      onTap: () {
        setState(() {
          if (alreadySaved) {
            _saved.remove(pair);
          } else {
            _saved.add(pair);
          }
          print("onTap=="+_saved.length.toString());
        });
      },
    );
  }

  void _pushSaved() {
    setState(() {

      int i = widget.suggestions.length;
   Todo todo =  new Todo(
      'Todo $i',
      'A description of what needs to be done for Todo $i',
    );
    widget.suggestions.add(todo);
    });
//    Navigator.of(context).push(
//      new MaterialPageRoute(
//        builder: (context) {
//          return new RandomWordsSelect(saved: _saved);
//        },
//      ),
//    );
  }

  void _handleCartChanged(Todo todo, bool isselect) {
    setState(() {
      if (isselect) {
        _saved.add(todo);
      } else {
        _saved.remove(todo);

      }
    });
  }

}
