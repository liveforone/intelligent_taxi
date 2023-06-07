package intelligent_taxi.dispatchservice.feignClient.constant;

public final class TaxiUrl {
    private TaxiUrl() {}

    public static final String BASE = "taxi-service";
    public static final String TAXI_INFO_BY_USERNAME = "/provide/taxi/info/username/{username}";
}
