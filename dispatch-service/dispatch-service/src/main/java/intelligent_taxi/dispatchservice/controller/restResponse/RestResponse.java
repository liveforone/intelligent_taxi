package intelligent_taxi.dispatchservice.controller.restResponse;

import org.springframework.http.ResponseEntity;

public class RestResponse {

    public static ResponseEntity<?> createDispatchSuccess() {
        return ResponseEntity
                .status(ResponseMessage.DISPATCH_SAVE_SUCCESS.getStatus())
                .body(ResponseMessage.DISPATCH_SAVE_SUCCESS.getValue());
    }
}
