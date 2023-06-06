package intelligent_taxi.dispatchservice.kafka;

public final class Topic {

    private Topic() {}

    //==producer==//
    public static final String REQUEST_ORDER = "request-order";
    public static final String REQUEST_CALCULATE = "request-calculate";

    //==consumer==//
    public static final String ORDER_FAIL_ROLLBACK_DISPATCH = "order-fail-rollback-dispatch";
    public static final String CALCULATE_FAIL_ROLLBACK_DISPATCH = "calculate-fail-rollback-dispatch";
}