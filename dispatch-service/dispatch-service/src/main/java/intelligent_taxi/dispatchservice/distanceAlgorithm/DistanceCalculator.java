package intelligent_taxi.dispatchservice.distanceAlgorithm;

public class DistanceCalculator {

    private static final double R = 6371; //지구의 평균 반지름 (단위: km)

    public static double calculateDistance(double presentLat, double presentLon, double destinationLat, double destinationLon) {
        //1.위도와 경도를 라디안 단위로 변환
        double radPreLat = Math.toRadians(presentLat);
        double radPreLon = Math.toRadians(presentLon);
        double radDesLat = Math.toRadians(destinationLat);
        double radDesLon = Math.toRadians(destinationLon);

        //2. 위도와 경도의 차이 계산
        double diffLat = radDesLat - radPreLat;
        double diffLon = radDesLon - radPreLon;

        //3. 대원거리 공식 적용
        double a = Math.sin(diffLat / 2) * Math.sin(diffLat / 2)
                + Math.cos(radPreLat) * Math.cos(radDesLat)
                * Math.sin(diffLon / 2) * Math.sin(diffLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        //4. 두 지점 사이의 거리 계산 (단위: km)
        return R * c;
    }
}
