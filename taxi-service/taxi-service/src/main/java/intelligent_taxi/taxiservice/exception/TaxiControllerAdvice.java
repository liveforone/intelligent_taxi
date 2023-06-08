package intelligent_taxi.taxiservice.exception;

import intelligent_taxi.taxiservice.controller.restResponse.RestResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaxiControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<?> duplicateEntityValue() {
        return RestResponse.duplicateEntityValue();
    }

    @ExceptionHandler(TaxiCustomException.class)
    protected ResponseEntity<?> taxiCustomHandle(TaxiCustomException customException) {
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
