package intelligent_taxi.taxiservice.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    TAXI("ROLE_TAXI"),
    MEMBER("ROLE_MEMBER");

    private String auth;
}
