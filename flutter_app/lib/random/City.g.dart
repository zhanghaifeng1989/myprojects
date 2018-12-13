// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'City.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

City _$CityFromJson(Map<String, dynamic> json) {
  return City(json['id'] as String, json['province'] as String,
      json['city'] as String, json['district'] as String);
}

Map<String, dynamic> _$CityToJson(City instance) => <String, dynamic>{
      'id': instance.id,
      'province': instance.province,
      'city': instance.city,
      'district': instance.district
    };
