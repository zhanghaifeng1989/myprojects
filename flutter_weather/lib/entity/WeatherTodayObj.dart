

import 'package:json_annotation/json_annotation.dart';
// user.g.dart 将在我们运行生成命令后自动生成
part 'WeatherTodayObj.g.dart';
@JsonSerializable()

class WeatherTodayObj {
final String temperature;//":"0℃~8℃",
final String weather;//":"多云",

final String wind;//":"西北风4-5级",
final String week;//":"星期三",
final String city;//":"苏州",
final String date_y;//":"2018年12月12日",
final String dressing_index;//":"较冷",
final String dressing_advice;//":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。",
final String uv_index;//":"最弱",
final String comfort_index;//":"",
final String wash_index;//":"较不宜",
final String travel_index;//":"较不宜",
final String exercise_index;//":"较不宜",
final String drying_index;//":""

WeatherTodayObj(this.temperature, this.weather,this.wind,this.week,this.city,this.date_y,this.dressing_index,this.dressing_advice,this.uv_index,this.comfort_index,this.wash_index,this.travel_index,this.exercise_index,this.drying_index);
//不同的类使用不同的mixin即可
factory WeatherTodayObj.fromJson(Map<String, dynamic> json) => _$WeatherTodayObjFromJson(json);
Map<String, dynamic> toJson() => _$WeatherTodayObjToJson(this);
}