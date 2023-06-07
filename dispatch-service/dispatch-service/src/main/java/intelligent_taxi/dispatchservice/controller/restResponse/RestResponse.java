package intelligent_taxi.dispatchservice.controller.restResponse;

import org.springframework.http.ResponseEntity;

public class RestResponse {

    public static ResponseEntity<?> createDispatchSuccess() {
        return ResponseEntity
                .status(ResponseMessage.DISPATCH_SAVE_SUCCESS.getStatus())
                .body(ResponseMessage.DISPATCH_SAVE_SUCCESS.getValue());
    }

    public static ResponseEntity<?> removeDispatchSuccess() {
        return ResponseEntity.ok(ResponseMessage.REMOVE_DISPATCH_SUCCESS.getValue());
    }
}
