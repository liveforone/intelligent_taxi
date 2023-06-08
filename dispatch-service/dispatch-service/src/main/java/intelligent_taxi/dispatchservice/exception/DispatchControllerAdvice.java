package intelligent_taxi.dispatchservice.exception;

import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DispatchControllerAdvice {

    public static ResponseEntity<?> duplicateEntityValue() {
        return ResponseEntity
                .status(ResponseMessage.DUPLICATE_ENTITY_VALUE.getStatus())
                .body(ResponseMessage.DUPLICATE_ENTITY_VALUE.getValue());
    }

    @ExceptionHandler(DispatchCustomException.class)
    protected ResponseEntity<?> dispatchCustomHandle(DispatchCustomException customException) {
        return ResponseEntity
                .status(customException.getResponseMessage().getStatus())
                .body(customException.getResponseMessage().getValue());
    }

    @ExceptionHandler(BindingCustomException.class)
    protected ResponseEntity<?> bindingErrorHandle(BindingCustomException customException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(customException.getMessage());
    }
}
