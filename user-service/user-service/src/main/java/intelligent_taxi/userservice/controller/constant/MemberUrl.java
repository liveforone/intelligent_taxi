package intelligent_taxi.userservice.controller.constant;

public final class MemberUrl {

    private MemberUrl() {}

    public static final String SIGNUP_MEMBER = "/signup/member";
    public static final String SIGNUP_TAXI = "/signup/taxi";
    public static final String LOGIN = "/login";
    public static final String MY_INFO = "/my-info";
    public static final String MY_BANKBOOK_NUM = "/provide/my-bankbookNum/{username}";
    public static final String CHANGE_EMAIL = "/change/email";
    public static final String CHANGE_PASSWORD = "/change/password";
    public static final String WITHDRAW = "/withdraw";
    public static final String PROHIBITION = "/prohibition";
    public static final String ACTUATOR = "/actuator/**";
}
