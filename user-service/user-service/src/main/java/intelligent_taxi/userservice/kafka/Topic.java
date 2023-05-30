package intelligent_taxi.userservice.kafka;

public final class Topic {

    private Topic() {}

    public static final String INCREASE_REPORT = "increase-report";
    public static final String CANCEL_BLOCK = "cancel-block";
    public static final String REMOVE_TAXI = "remove-taxi-belong-member";
}