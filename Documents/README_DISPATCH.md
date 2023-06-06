# 배차 서비스

(현위치+목적지위치, 배차 시스템, 기사가 진행중이 운행이 있으면 배차 불가능, 즉 기사가 배차 신청하여 자동배차 될때 운행중인 운행이 있으면 배제시키기)



배차 페이지는 username으로 리턴하는데 가장 id가 최신인 것 하나를 리턴한다.
```
SELECT *
FROM a
WHERE username = 'kim'
ORDER BY id DESC
LIMIT 1;
```

사용자가 직접 나의 배차 요청에 들어가서
지속적으로 새로고침하여서 배차가 성공했는지 확인하게 하는 것도 방법(이경우 서버에 손실이 아주 적음. 알림에 비해)

사용자 배차 요청 -> 결제 하여 금액 확인 -> 배차 등록
-> 기사 배차 요청 -> 배차완료 -> 사용자 결제 -> 기사 정산 -> 주문 등록 -> 운행시스템 등록 -> 운행완료 -> 기사가 운행 완료 눌러 저장 -> 리뷰 가능

## 배차성공
배차 성공 
-> 주문처리
-> 운행시스템에 정산처리
-> 사용자 배차정보 갱신
-> 택시 기사 화면제공(목적지(좌표), 거리)

거리의 경우 1km, 3km, 5km 순으로 올라가면서 택시를 찾고,
5km이상 부터없을시에는 있을때까지 대기.

승객이 call을 함 -> dispatch 목록에 올라감(대기)
택시가 배차를 요청함 -> 자동배차
택시가 배차를 요청하면 택시의 현 위치가 입력됨
이를 바탕으로 call db에서 가장 가까운 콜을 잡아서 자동 배차해줌.

배차시 동시성 문제가 발생할 가능성이 많음
업데이트 시에 배차 state를 배차 완료로하여서 다른 프로세스에서
조회를 못하도록 막음
배차가 끝남과 동시에 사용자의 주문에는 배차 정보가 그대로 기입되어야함
배차 등록시 선불이기 때문에 주문으로 정보를 전달할땐 kafka로 전달하기
또한 배차가 완료되면 취소가 불가능하므로 order에 전달하고,
택시기사의 order로도 전달해야한다.

배차 state가 배차완료가 아닐경우 배차 취소 가능
배차취소시 -> 배차먼저 삭제 -> 환불(100%)

결제는 요청만하고, 결제취소도 요청만 하지, 절대로 결제에 깊게 관여해서는 안된다.

기사가 배차요청을할때 나의 위치 기준으로 저장된 배차중에 가장 가까운것을 잘 찾아오는 것이 중요함

## 배차
배차시에 지역은 이미 좌표로 나타내어서 필요가 없지만
택시 등급은 필요하다.
애초에 배차시에 택시 등급별로 배차잡는것을 다르게 할당할것이기에.
배차 정보에 택시 등급을 넣는것도 방법이다.

## 자동배차의 이유 - 고민점? 여튼 문서화 아래 알고리즘 문서와 함께 정리하기
콜만 띄우는 형태는 큰 의미가 없다.
그냥 콜택시 앱일 뿐이다. 강제 배차 시스템을 넣는것이 핵심이다.
모든것은 좌표로 이루어지고, 모든 것은 거리로 계산한다.
기준 거리 안에 있다면 강제 배차가 가까운 거리순으로 이루어지도록 하는것이 좋다.

## 위치 시스템 - 정리하고 또 따로 문서화하기
* 대부분의 지도시스템은 위도와 경도 체계인 WGS84(World Geodetic System 1984)을 따름
* 위도(latitude): 소수점 이하 6자리까지 표현됩니다. 일반적으로 1m 정도의 정확도를 가집니다. 가로값으로 생각하면 됩니다.
* 경도(longitude): 소수점 이하 6자리까지 표현됩니다. 위도와 마찬가지로 1m 정도의 정확도를 가집니다. 세로값으로 생각하면 됩니다.
* 위도는 -90에서 +90까지이고, 경도는 -180에서 +180까지 값을 갖습니다.
* 두 위도와 경도를 알때 거리를 구하는 공식은 아래와 같음
* 거리 = 2 * R * arcsin(√(sin²(diff_lat/2) + cos(rad₁) * cos(rad₂) * sin²(diff_lon/2)))
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