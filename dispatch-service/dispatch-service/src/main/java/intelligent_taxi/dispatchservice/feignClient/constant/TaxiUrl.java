package intelligent_taxi.dispatchservice.feignClient.constant;

public final class TaxiUrl {
    private TaxiUrl() {}

    public static final String BASE = "taxi-service";
    public static final String TAXI_INFO = "/provide/taxi/info/{id}";
}
