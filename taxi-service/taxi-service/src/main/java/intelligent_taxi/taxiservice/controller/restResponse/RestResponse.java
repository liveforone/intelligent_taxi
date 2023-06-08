package intelligent_taxi.taxiservice.controller.restResponse;

import org.springframework.http.ResponseEntity;

public class RestResponse {

    public static ResponseEntity<?> duplicateEntityValue() {
        return ResponseEntity
                .status(ResponseMessage.DUPLICATE_ENTITY_VALUE.getStatus())
                .body(ResponseMessage.DUPLICATE_ENTITY_VALUE.getValue());
    }

    public static ResponseEntity<?> createTaxiSuccess() {
        return ResponseEntity
                .status(ResponseMessage.CREATE_TAXI_SUCCESS.getStatus())
                .body(ResponseMessage.CREATE_TAXI_SUCCESS.getValue());
    }

    public static ResponseEntity<?> updateRegionSuccess() {
        return ResponseEntity.ok(ResponseMessage.UPDATE_REGION_SUCCESS.getValue());
    }

    public static ResponseEntity<?> updateGradeSuccess() {
        return ResponseEntity.ok(ResponseMessage.UPDATE_GRADE_SUCCESS.getValue());
    }

    public static ResponseEntity<?> deleteTaxiSuccess() {
        return ResponseEntity.ok(ResponseMessage.DELETE_TAXI_SUCCESS.getValue());
    }
}
