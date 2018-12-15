// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'Response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ResponseBody _$ResponseBodyFromJson(Map<String, dynamic> json) {
  return ResponseBody(
      json['resultcode'] as String,
      json['reason'] as String,
      json['result'] == null
          ? null
          : WeatherResponseBody.fromJson(
              json['result'] as Map<String, dynamic>));
}

Map<String, dynamic> _$ResponseBodyToJson(ResponseBody instance) =>
    <String, dynamic>{
      'resultcode': instance.resultcode,
      'reason': instance.reason,
      'result': instance.result
    };
