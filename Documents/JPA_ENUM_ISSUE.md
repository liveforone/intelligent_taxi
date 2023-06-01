# enum 저장 이슈

## 이슈
* 스프링 부트 버전을 3점 대로 변경하고 난후 
* enum을 string으로 저장하도록 설정해도 어노테이션이 먹히지 않고 enum 그대로 저장이 되었다.
* 이에 따라 enum converter를 만들어서 문자열로 변환해 저장이 가능하도록 바꾸었다.
* Enumerated의 경우 enum의 값이 바뀌어서 db에 있는 값이 enum에 존재하지 않으면 에러가 발생하는 단점도 있었다.
* 따라서 이참에 converter를 이용해 값을 바꾸어주도록 하였다.

## 굳이 그렇게까지? 그냥 enum으로 저장하면 안되나?
* [enum을 저장하지 말아야할 이유](https://velog.io/@leejh3224/%EB%B2%88%EC%97%AD-MySQL%EC%9D%98-ENUM-%ED%83%80%EC%9E%85%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%ED%95%A0-8%EA%B0%80%EC%A7%80-%EC%9D%B4%EC%9C%A0)를 보면 알 수 있듯이 enum을 그대로 db에 저장하는 것은 많은 악영향을 가져온다.
* 따라서 이를 문자열로 바꾸어 저장하는 것이 더욱좋다.
* enum자체의 name()함수나, getter가 있다면 getter를 이용해서 상수의 값을 가져오는 것은 일도 아니다.
* 컨버터가 부담스럽다면 저런 함수들을 활용해서 문자열로 직접 변환하여 저장해도 된다.
* enum 그 자체를 저장하진 말자.

## converter 사용 예시
### 엔티티 필드
```
@Convert(converter = RegionConverter.class)
@Column(nullable = false)
private Region region;
```
### Enum Region
```
@Getter
@AllArgsConstructor
public enum Region {

    //==수도권==//
    SEOUL("서울"), GYEONGGI("경기"), INCHEON("인천"),
    //==강원==//
    GANGWON("강원"),
    //==충청==//
    CHUNGBUK("충북"), CHUNGNAM("충남"), DAEJEON("대전"),
    //==경상==//
    GYEONGBUK("경북"), GYEONGNAM("경남"), DAEGU("대구"), ULSAN("울산"), BUSAN("부산"),
    //==전라==//
    JEOLLABUK("전북"), JEOLLANAM("전남"), GWANGJU("광주"),
    //==제주==//
    JEJU("제주");
    
    private final String region;
}
```
### converter
* AttributeConverter안에는 <이넘타입, 변환을 원하는 값>으로 넣으면 된다.
* 두개의 함수를 오버라이드 하면 끝난다.
* response로 클라이언트에게 상수 값을 전달하고자 하더라도, converter는 실제 데이터와 enum간의 통신이기 때문에
* 헷갈리지 말고 attribute.name()으로 enum 상수를 반환하도록 하자. 
* attribute.getRegion()을 사용하면 없는 값을 변환한다고 에러가 발생한다!
* 컨버터는 딱 db의 값과 enum 타입간의 상호 변환자라는 것을 기억하자.
```
@Converter
public class RegionConverter implements AttributeConverter<Region, String> {
    @Override
    public String convertToDatabaseColumn(Region attribute) {
        return attribute.name();
    }

    @Override
    public Region convertToEntityAttribute(String dbData) {
        return Region.valueOf(dbData);
    }
}
```