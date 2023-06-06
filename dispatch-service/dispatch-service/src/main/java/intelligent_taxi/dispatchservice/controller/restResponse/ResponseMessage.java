package intelligent_taxi.dispatchservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    AUTH_IS_NOT_MEMBER(401, "일반회원이 아닙니다."),
    DISPATCH_SAVE_SUCCESS(201, "배차를 성공적으로 등록하였습니다.");

    private final int status;
    private final String value;
}
