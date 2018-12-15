import 'package:json_annotation/json_annotation.dart';
// user.g.dart 将在我们运行生成命令后自动生成
part 'City.g.dart';
@JsonSerializable()

class City {
  final String id;
  final String province;
  final String city;
  final String district;
  City(this.id, this.province,this.city,this.district);
  //不同的类使用不同的mixin即可
  factory City.fromJson(Map<String, dynamic> json) => _$CityFromJson(json);
  Map<String, dynamic> toJson() => _$CityToJson(this);
//  City.fromJson(Map<String, dynamic> json)
//      : id = json['id'],
//        province = json['province'],
//        city = json['city'],
//        district = json['district'];
}