// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'WeatherResponseBody.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

WeatherResponseBody _$WeatherResponseBodyFromJson(Map<String, dynamic> json) {
  return WeatherResponseBody(
      json['sk'] == null
          ? null
          : WeatherSkObj.fromJson(json['sk'] as Map<String, dynamic>),
      json['today'] == null
          ? null
          : WeatherTodayObj.fromJson(json['today'] as Map<String, dynamic>));
}

Map<String, dynamic> _$WeatherResponseBodyToJson(
        WeatherResponseBody instance) =>
    <String, dynamic>{'sk': instance.sk, 'today': instance.today};
