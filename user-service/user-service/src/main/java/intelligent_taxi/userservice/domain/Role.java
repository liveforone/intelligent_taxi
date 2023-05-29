package intelligent_taxi.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    TAXI("ROLE_TAXI"),
    MEMBER("ROLE_MEMBER");

    private String value;
}
