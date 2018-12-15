import 'package:json_annotation/json_annotation.dart';
import 'package:flutter_weather/entity/WeatherSkObj.dart';
import 'package:flutter_weather/entity/WeatherTodayObj.dart';

// user.g.dart 将在我们运行生成命令后自动生成
part 'WeatherResponseBody.g.dart';

@JsonSerializable()

class WeatherResponseBody {
  final WeatherSkObj sk;
  final WeatherTodayObj today;
  WeatherResponseBody(this.sk, this.today);
//不同的类使用不同的mixin即可
  factory WeatherResponseBody.fromJson(Map<String, dynamic> json) => _$WeatherResponseBodyFromJson(json);
  Map<String, dynamic> toJson() => _$WeatherResponseBodyToJson(this);
}