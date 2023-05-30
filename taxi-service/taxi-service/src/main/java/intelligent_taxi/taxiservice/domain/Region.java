package intelligent_taxi.taxiservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
