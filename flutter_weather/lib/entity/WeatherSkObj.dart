
import 'package:json_annotation/json_annotation.dart';
// user.g.dart 将在我们运行生成命令后自动生成
part 'WeatherSkObj.g.dart';
@JsonSerializable()

class WeatherSkObj {
final String temp;
final String wind_direction;
final String wind_strength;
final String humidity;
final String time;
WeatherSkObj(this.temp, this.wind_direction,this.wind_strength,this.humidity,this.time);
//不同的类使用不同的mixin即可
factory WeatherSkObj.fromJson(Map<String, dynamic> json) => _$WeatherSkObjFromJson(json);
Map<String, dynamic> toJson() => _$WeatherSkObjToJson(this);
}