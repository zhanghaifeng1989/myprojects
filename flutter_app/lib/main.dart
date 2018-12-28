import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_app/random/randomwordslist.dart';
import 'package:flutter_app/random/Todo.dart';

import 'package:dio/dio.dart';




/****************************************列表案例*****************************************/
void main() => runApp(new MyApp());
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

      final _suggestions = new List<Todo>.generate(
    20,
        (i) => new Todo(
      'Todo $i',
      'A description of what needs to be done for Todo $i',
    ),
  );

    return new MaterialApp(
      title: 'Startup Name Generator',
      theme: new ThemeData(
        primaryColor: Colors.white,
      ),
      home: new RandomWords(suggestions: _suggestions),
    );
  }
}
/************************************列表案例*****************************************/



//class MyAppBar extends StatelessWidget {
//  MyAppBar({this.title});
//
//  // Widget子类中的字段往往都会定义为"final"
//
//  final Widget title;
//
//  @override
//  Widget build(BuildContext context) {
//    return new Container(
//      height: 56.0, // 单位是逻辑上的像素（并非真实的像素，类似于浏览器中的像素）
//      padding: const EdgeInsets.symmetric(horizontal: 8.0),
//      decoration: new BoxDecoration(color: Colors.blue[500]),
//      // Row 是水平方向的线性布局（linear layout）
//      child: new Row(
//        //列表项的类型是 <Widget>
//        children: <Widget>[
//          new IconButton(
//            icon: new Icon(Icons.menu),
//            tooltip: 'Navigation menu',
//            onPressed: null, // null 会禁用 button
//          ),
//          // Expanded expands its child to fill the available space.
//          new Expanded(
//            child: title,
//          ),
//          new IconButton(
//            icon: new Icon(Icons.search),
//            tooltip: 'Search',
//            onPressed: null,
//          ),
//        ],
//      ),
//    );
//  }
//}
//
//class MyScaffold extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    // Material 是UI呈现的“一张纸”
//    return new Material(
//      // Column is 垂直方向的线性布局.
//      child: new Column(
//        children: <Widget>[
//          new MyAppBar(
//            title: new Text(
//              'Example title',
//              style: Theme.of(context).primaryTextTheme.title,
//            ),
//          ),
//          new Expanded(
//            child: new Center(
//              child: new Text('Hello, world!'),
//            ),
//          ),
//        ],
//      ),
//    );
//  }
//}
//
//class TutorialHome extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    //Scaffold是Material中主要的布局组件.
//    return new Scaffold(
//      appBar: new AppBar(
//        leading: new IconButton(
//          icon: new Icon(Icons.menu),
//          tooltip: 'Navigation menu',
//          onPressed: null,
//        ),
//        title: new Text('Example title'),
//        actions: <Widget>[
//          new IconButton(
//            icon: new Icon(Icons.search),
//            tooltip: 'Search',
//            onPressed: null,
//          ),
//        ],
//      ),
//      //body占屏幕的大部分
//      body: new Center(
//        child: new Text('Hello, world!'),
//      ),
//      floatingActionButton: new FloatingActionButton(
//        tooltip: 'Add', // used by assistive technologies
//        child: new Icon(Icons.add),
//        onPressed: null,
//      ),
//    );
//  }
//}
////手势
//class MyButton extends StatelessWidget {
//  @override
//  Widget build(BuildContext context) {
//    return new GestureDetector(
//      onTap: () {
//        print('MyButton was tapped!');
//      },
//      child: new Container(
//        height: 36.0,
//        padding: const EdgeInsets.all(8.0),
//        margin: const EdgeInsets.symmetric(horizontal: 8.0),
//        decoration: new BoxDecoration(
//          borderRadius: new BorderRadius.circular(5.0),
//          color: Colors.lightGreen[500],
//        ),
//        child: new Center(
//          child: new Text('Engage'),
//        ),
//      ),
//    );
//  }
//}
//
////StatefulWidget
//class Counter extends StatefulWidget {
//  // This class is the configuration for the state. It holds the
//  // values (in this nothing) provided by the parent and used by the build
//  // method of the State. Fields in a Widget subclass are always marked "final".
//
//  @override
//  _CounterState createState() => new _CounterState();
//}
//class _CounterState extends State<Counter> {
//  int _counter = 0;
//
//  void _increment() {
//    setState(() {
//      // This call to setState tells the Flutter framework that
//      // something has changed in this State, which causes it to rerun
//      // the build method below so that the display can reflect the
//      // updated values. If we changed _counter without calling
//      // setState(), then the build method would not be called again,
//      // and so nothing would appear to happen.
//      _counter++;
//    });
//  }
//
//  @override
//  Widget build(BuildContext context) {
//    // This method is rerun every time setState is called, for instance
//    // as done by the _increment method above.
//    // The Flutter framework has been optimized to make rerunning
//    // build methods fast, so that you can just rebuild anything that
//    // needs updating rather than having to individually change
//    // instances of widgets.
//    return new Row(
//      children: <Widget>[
//        new RaisedButton(
//          onPressed: _increment,
//          child: new Text('Increment'),
//        ),
//        new Text('Count: $_counter'),
//      ],
//    );
//  }
//}



/****************************************购物车案例*****************************************
class Product {
  const Product({this.name});
  final String name;
}

typedef void CartChangedCallback(Product product, bool inCart);

class ShoppingListItem extends StatelessWidget {
  ShoppingListItem({Product product, this.inCart, this.onCartChanged})
      : product = product,
        super(key: new ObjectKey(product));

  final Product product;
  final bool inCart;
  final CartChangedCallback onCartChanged;

  Color _getColor(BuildContext context) {
    // The theme depends on the BuildContext because different parts of the tree
    // can have different themes.  The BuildContext indicates where the build is
    // taking place and therefore which theme to use.

    return inCart ? Colors.black54 : Theme.of(context).primaryColor;
  }

  TextStyle _getTextStyle(BuildContext context) {
    if (!inCart) return null;

    return new TextStyle(
      color: Colors.black54,
      decoration: TextDecoration.lineThrough,
    );
  }

  @override
  Widget build(BuildContext context) {
    return new ListTile(
      onTap: () {
        onCartChanged(product, !inCart);
      },
      leading: new CircleAvatar(
        backgroundColor: _getColor(context),
        child: new Text(product.name[0]),
      ),
      title: new Text(product.name, style: _getTextStyle(context)),
    );
  }
}
class ShoppingList extends StatefulWidget {
  ShoppingList({Key key, this.products}) : super(key: key);

  final List<Product> products;

  // The framework calls createState the first time a widget appears at a given
  // location in the tree. If the parent rebuilds and uses the same type of
  // widget (with the same key), the framework will re-use the State object
  // instead of creating a new State object.

  @override
  _ShoppingListState createState() => new _ShoppingListState();
}

class _ShoppingListState extends State<ShoppingList> {
  Set<Product> _shoppingCart = new Set<Product>();

  void _handleCartChanged(Product product, bool inCart) {
    setState(() {
      // When user changes what is in the cart, we need to change _shoppingCart
      // inside a setState call to trigger a rebuild. The framework then calls
      // build, below, which updates the visual appearance of the app.

      if (inCart)
        _shoppingCart.add(product);
      else
        _shoppingCart.remove(product);
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('Shopping List'),
      ),
      body: new ListView(
        padding: new EdgeInsets.symmetric(vertical: 8.0),
        children: widget.products.map((Product product) {
          return new ShoppingListItem(
            product: product,
            inCart: _shoppingCart.contains(product),
            onCartChanged: _handleCartChanged,
          );
        }).toList(),
      ),
    );
  }
}

void main() {
  runApp(new MaterialApp(
    title: 'Shopping App',
    home: new ShoppingList(
      products: <Product>[
        new Product(name: 'Eggs'),
        new Product(name: 'Flour'),
        new Product(name: 'Chocolate chips'),
      ],
    ),
  ));
}
****************************************购物车案例*****************************************/


/****************************************布局案例****************************************
void main() => runApp(new MyApp());
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new Scaffold(
        body: new ListView(
          children: [
            new Image.asset(
              'images/lake.jpg',
              width: 600.0,
              height: 240.0,
              fit: BoxFit.cover,
            ),
            titleSection,
            buttonSection,
            textSection,
          ],
        ),
      ),
    );

  }

  Widget titleSection = new Container(
    padding: const EdgeInsets.all(32.0),
    child: new Row(
      children: [
        new Expanded(
          child: new Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              new Container(
                padding: const EdgeInsets.only(bottom: 8.0),
                child: new Text(
                  'Oeschinen Lake Campground',
                  style: new TextStyle(
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              new Text(
                'Kandersteg, Switzerland',
                style: new TextStyle(
                  color: Colors.grey[500],
                ),
              ),
            ],
          ),
        ),
        new Icon(
          Icons.star,
          color: Colors.red[500],
        ),
        new Text('41'),
      ],
    ),
  );

    Widget textSection = new Container(
      padding: const EdgeInsets.all(32.0),
      child: new Text(
        '''
Lake Oeschinen lies at the foot of the Blüemlisalp in the Bernese Alps. Situated 1,578 meters above sea level, it is one of the larger Alpine Lakes. A gondola ride from Kandersteg, followed by a half-hour walk through pastures and pine forest, leads you to the lake, which warms to 20 degrees Celsius in the summer. Activities enjoyed here include rowing, and riding the summer toboggan run.
        ''',
        softWrap: true,
      ),
    );
  Widget buttonSection = new Container(
    child: new Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        buildButtonColumn(Icons.call, 'CALL'),
        buildButtonColumn(Icons.near_me, 'ROUTE'),
        buildButtonColumn(Icons.share, 'SHARE'),
      ],
    ),
  );
}

Column buildButtonColumn(IconData icon, String label) {
  Color color = Colors.blue;

  return new Column(
    mainAxisSize: MainAxisSize.min,
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      new Icon(icon, color: color),
      new Container(
        margin: const EdgeInsets.only(top: 8.0),
        child: new Text(
          label,
          style: new TextStyle(
            fontSize: 12.0,
            fontWeight: FontWeight.w400,
            color: color,
          ),
        ),
      ),
    ],
  );
}

***************************************布局案例*****************************************/


/***************************************viewpager案例****************************************

class AppBarBottomSample extends StatefulWidget {
  @override
  _AppBarBottomSampleState createState() => new _AppBarBottomSampleState();
}

class _AppBarBottomSampleState extends State<AppBarBottomSample> with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = new TabController(vsync: this, length: choices.length);
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
            onPressed: () { _nextPage(-1); },
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
          children: choices.map((Choice choice) {
            return new Padding(
              padding: const EdgeInsets.all(16.0),
              child: new ChoiceCard(choice: choice),
            );
          }).toList(),
        ),
      ),
    );
  }
}

class Choice {
  const Choice({ this.title, this.icon });
  final String title;
  final IconData icon;
}

const List<Choice> choices = const <Choice>[
  const Choice(title: 'CAR', icon: Icons.directions_car),
  const Choice(title: 'BICYCLE', icon: Icons.directions_bike),
  const Choice(title: 'BOAT', icon: Icons.directions_boat),
  const Choice(title: 'BUS', icon: Icons.directions_bus),
  const Choice(title: 'TRAIN', icon: Icons.directions_railway),
  const Choice(title: 'WALK', icon: Icons.directions_walk),
];

class ChoiceCard extends StatelessWidget {
  const ChoiceCard({ Key key, this.choice }) : super(key: key);

  final Choice choice;

  @override
  Widget build(BuildContext context) {
    final TextStyle textStyle = Theme.of(context).textTheme.display1;
    return new Card(
      color: Colors.white,
      child: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            new Icon(choice.icon, size: 128.0, color: textStyle.color),
            new Text(choice.title, style: textStyle),
          ],
        ),
      ),
    );
  }
}

void main() {
  runApp(new AppBarBottomSample());
}
*************************************viewpager案例*****************************************/

/***************************************diohttp案例****************************************
import 'package:flutter_app/random/City.dart';
import 'package:flutter_app/random/Response.dart';
import 'package:flutter_app/random/HttpManager.dart';
import 'package:flutter_app/random/WeatherResponseBody.dart';
import 'package:flutter_app/random/WeatherSkObj.dart';
void main() {
  runApp(new MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new MyHomePage(),
    );
  }
}
typedef void OnWeatherCallback(ResponseBody resp);

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var _ipAddress = 'Unknown';
   bool hideprogress = true;
  getWeather(){
    setState(() {
      hideprogress = false;
    });
    getWeatherHttp(handlerWeatherCallback,"哈尔滨");
  }
  handlerWeatherCallback(ResponseBody resp){
//    print("handlerWeatherCallback");
//    print("resp======"+resp.toString());
    WeatherSkObj obj  =  resp.result.sk;
//    print("WeatherSkObj======"+obj.toString());
    String result = obj.temp;
    setState(() {
      _ipAddress = result;
      hideprogress = true;
    });
  }

  @override
  Widget build(BuildContext context) {
    var spacer = new SizedBox(height: 32.0);

    return new Scaffold(
      body: new Center(
        child: new Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            new Text('Your current IP address is:'),
            new Text('$_ipAddress.'),
            new Offstage(
              offstage: hideprogress,
              child: new CircularProgressIndicator(valueColor: AlwaysStoppedAnimation(Colors.black26)),
            ),
            spacer,
            new RaisedButton(
              onPressed: getWeather,
              child: new Text('Get IP address'),
            ),
          ],
        ),
      ),
    );
  }
}
*/







