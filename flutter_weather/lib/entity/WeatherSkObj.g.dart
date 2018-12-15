// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'WeatherSkObj.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

WeatherSkObj _$WeatherSkObjFromJson(Map<String, dynamic> json) {
  return WeatherSkObj(
      json['temp'] as String,
      json['wind_direction'] as String,
      json['wind_strength'] as String,
      json['humidity'] as String,
      json['time'] as String);
}

Map<String, dynamic> _$WeatherSkObjToJson(WeatherSkObj instance) =>
    <String, dynamic>{
      'temp': instance.temp,
      'wind_direction': instance.wind_direction,
      'wind_strength': instance.wind_strength,
      'humidity': instance.humidity,
      'time': instance.time
    };
