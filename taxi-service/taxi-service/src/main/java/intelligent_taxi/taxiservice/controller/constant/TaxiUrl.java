package intelligent_taxi.taxiservice.controller.constant;

public final class TaxiUrl {

    //==query==//
    public static final String MY_PAGE = "/taxi/my-page";
    public static final String TAXI_INFO = "/provide/taxi/info/{id}";

    //==command==//
    public static final String CREATE = "/create/taxi";
    public static final String UPDATE_REGION = "/update/region/{id}";
    public static final String UPDATE_GRADE = "/update/grade/{id}";
    public static final String DELETE_TAXI = "/delete/taxi/{id}";
}
