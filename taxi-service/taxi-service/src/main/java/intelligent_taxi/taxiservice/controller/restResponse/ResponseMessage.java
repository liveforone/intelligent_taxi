package intelligent_taxi.taxiservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    NOT_TAXI_MEMBER(401, "택시 권한의 회원이 아닙니다."),
    NOT_EXIST_REGION(404, "존재하지 않는 지역입니다."),
    NOT_EXIST_GRADE(404, "존재하지 않는 택시 등급입니다."),
    SAVE_TAXI_SUCCESS(201, "택시 등록에 성공하였습니다.");

    private final int status;
    private final String value;
}
