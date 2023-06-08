package intelligent_taxi.taxiservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    //==SUCCESS==//
    CREATE_TAXI_SUCCESS(201, "택시 등록에 성공하였습니다."),
    UPDATE_REGION_SUCCESS(200, "지역 변경에 성공하였습니다."),
    UPDATE_GRADE_SUCCESS(200, "등급 변경에 성공하였습니다."),
    DELETE_TAXI_SUCCESS(200, "택시 등록을 성공적으로 삭제하였습니다."),

    //==FAIL==//
    DUPLICATE_ENTITY_VALUE(400, "중복되는 데이터가 존재합니다."),
    TAXI_IS_NULL(404, "존재하지 않는 택시입니다."),
    NOT_TAXI_MEMBER(401, "택시 권한의 회원이 아닙니다."),
    NOT_EXIST_REGION(404, "존재하지 않는 지역입니다."),
    NOT_EXIST_GRADE(404, "존재하지 않는 택시 등급입니다."),
    NOT_MATCH_USERNAME(400, "기사 정보가 일치하지 않습니다.");

    private final int status;
    private final String value;
}
