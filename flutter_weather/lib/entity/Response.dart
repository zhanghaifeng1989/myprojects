import 'package:flutter_weather/entity/WeatherResponseBody.dart';
import 'package:json_annotation/json_annotation.dart';
// user.g.dart 将在我们运行生成命令后自动生成
part 'Response.g.dart';
@JsonSerializable()
class ResponseBody {
  final String resultcode;
  final String reason;
  final WeatherResponseBody result;
  ResponseBody(this.resultcode, this.reason,this.result);
  factory ResponseBody.fromJson(Map<String, dynamic> json) => _$ResponseBodyFromJson(json);
  Map<String, dynamic> toJson() => _$ResponseBodyToJson(this);
}