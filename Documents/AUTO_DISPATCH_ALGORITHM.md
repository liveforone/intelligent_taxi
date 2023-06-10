# 배차 알고리즘

## 자동배차
* 배차는 모두 자동으로 이루어집니다.
* 회원들이 수동으로 하는 것은 배차를 등록하는 것과
* 배차를 요청하는 것 뿐입니다.

## 자동배차의 이유
* 콜만 띄우는 형태는 게시판과 별반 다를 바가 없다고 생각이 들었습니다.
* 또한 기사가 골라가는 문제가 발생하여, 나가지 않는 배차가 반드시 발생할 것이라고 생각했습니다.
* 이러한 이유로 자동배차 기능을 고민하게 되었습니다.

## 위치 시스템
* 대부분의 지도시스템은 위도와 경도 체계인 WGS84(World Geodetic System 1984)을 따름
* 위도(latitude): 소수점 이하 6자리까지 표현됩니다. 일반적으로 1m 정도의 정확도를 가집니다. 가로값으로 생각하면 됩니다.
* 경도(longitude): 소수점 이하 6자리까지 표현됩니다. 위도와 마찬가지로 1m 정도의 정확도를 가집니다. 세로값으로 생각하면 됩니다.
* 위도는 -90에서 +90까지이고, 경도는 -180에서 +180까지 값을 갖습니다.
* 두 위도와 경도를 알때 거리를 구하는 공식은 대원거리 공식이며, 공식을 사용하며 아래와 같습니다.
* 거리 = 2 * R * arcsin(√(sin²(diff_lat/2) + cos(rad₁) * cos(rad₂) * sin²(diff_lon/2)))
* 아래는 대원거리고 공식으로 거리를 구하는 것을 자바 코드로 표현한 것입니다.
```
import java.lang.Math;

public class HaversineDistanceCalculator {
    private static final double R = 6371; // 지구의 평균 반지름 (단위: km)

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 위도와 경도를 라디안 단위로 변환
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // 위도와 경도의 차이 계산
        double diffLat = radLat2 - radLat1;
        double diffLon = radLon2 - radLon1;

        // 대원거리 공식 적용
        double a = Math.sin(diffLat / 2) * Math.sin(diffLat / 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.sin(diffLon / 2) * Math.sin(diffLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 두 지점 사이의 거리 계산 (단위: km)
        double distance = R * c;
        return distance;
    }

    public static void main(String[] args) {
        // 예시: 서울과 뉴욕의 거리 계산
        double seoulLat = 37.5665;
        double seoulLon = 126.9780;
        double newYorkLat = 40.7128;
        double newYorkLon = -74.0060;

        double distance = calculateDistance(seoulLat, seoulLon, newYorkLat, newYorkLon);
        System.out.println("서울과 뉴욕 사이의 거리: " + distance + " km");
    }
}
```

## 쿼리에서 자동배차
* 배차 요청을 하면, 가장 가까운 거리로 등록된 배차를 조회하여
* 자동 배차 시켜줍니다.
* query dsl 쿼리와 대원거리 공식을 사용해 작성한 코드는 아래와 같습니다.
* optional은 null safe하게 만들기 위한 것으로 무시하셔도 좋습니다.
```
public Optional<Dispatch> findOneWithinDistance(Double latitude, Double longitude) {
    NumberExpression<Double> calculatedDistance = calculateDistance(latitude, longitude, dispatch.presentLatitude, dispatch.presentLongitude);

    return Optional.ofNullable(queryFactory
            .selectFrom(dispatch)
            .where(
                    calculatedDistance.loe(ONE_KM)
                        .or(calculatedDistance.loe(THREE_KM))
                        .or(calculatedDistance.loe(FIVE_KM))
            )
            .limit(1)
            .fetchOne());
}

private NumberExpression<Double> calculateDistance(Double lat1, Double lon1, NumberExpression<Double> lat2, NumberExpression<Double> lon2) {
    double lat1Rad = lat1 * Math.PI / 180.0;
    double lon1Rad = lon1 * Math.PI / 180.0;

    NumberTemplate<Double> lat1Num = numberTemplate(Double.class, "{0}", lat1Rad);
    NumberExpression<Double> lat2Rad = lat2.multiply(Math.PI).divide(180.0);
    NumberExpression<Double> lon2Rad = lon2.multiply(Math.PI).divide(180.0);

    NumberExpression<Double> lonDiff = lon2Rad.subtract(lon1Rad);

    NumberExpression<Double> a = sin(lat1Num).multiply(sin(lat2Rad))
        .add(cos(lat1Num).multiply(cos(lat2Rad)).multiply(cos(lonDiff)));

    return acos(a).multiply(R);
}
```

## 대원거리 공식의 활용
* 대원거리 공식을 해당 프로젝트에서는 최단 직선거리만을 구하는데에 사용했습니다.
* 그런데 네비게이션 같은 것을 구현해야하는 경우에는 최단 직선거리로는 거리를 책정할 수 없습니다.
* 도로를 따라 도로의 거리를 계산해야하기 때문입니다.
* 이러한 경우에는 도로가 꺽이는 부분마다 좌표를 기입하여 직선들의 거리를 모두 구한 후
* 이들을 더하여 도로의 거리를 계산할 수 있습니다.
```
\
/
\
 --
의 형태라면
도로가 꺽이는 부분마다 좌표를 찍어서 직선들의 거리를 구하고
이를 모두 더해 연결하면 됩니다.
```