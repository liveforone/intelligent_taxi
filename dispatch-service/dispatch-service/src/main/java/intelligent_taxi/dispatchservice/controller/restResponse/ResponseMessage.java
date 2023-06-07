package intelligent_taxi.dispatchservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    //==success==//
    DISPATCH_SAVE_SUCCESS(201, "배차를 성공적으로 등록하였습니다."),
    REMOVE_DISPATCH_SUCCESS(200, "배차를 성공적으로 등록 취소 하였습니다."),
    DISPATCH_SUCCESS(201, "배차를 성공하였습니다."),

    //==fail==//
    CHECK_BALANCE_FAIL(400, "결제 승인에 실패하였습니다."),
    AUTH_IS_NOT_MEMBER(401, "일반회원이 아닙니다."),
    AUTH_IS_NOT_TAXI(401, "택시회원이 아닙니다."),
    DISPATCH_IS_NULL(404, "배차가 존재하지 않습니다."),
    NOT_MATCH_USERNAME(400, "일치하지 않는 회원입니다."),
    DISPATCH_IS_ALREADY_FINISH(400, "이미 배차가 완료되었습니다.\n취소가 불가능합니다.");

    private final int status;
    private final String value;
}
