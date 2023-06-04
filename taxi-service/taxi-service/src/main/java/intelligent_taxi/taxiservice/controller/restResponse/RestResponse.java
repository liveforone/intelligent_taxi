package intelligent_taxi.taxiservice.controller.restResponse;

import org.springframework.http.ResponseEntity;

public class RestResponse {

    public static ResponseEntity<?> createTaxiSuccess() {
        return ResponseEntity
                .status(ResponseMessage.CREATE_TAXI_SUCCESS.getStatus())
                .body(ResponseMessage.CREATE_TAXI_SUCCESS.getValue());
    }
}