package intelligent_taxi.taxiservice.exception;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import lombok.Getter;

@Getter
public class TaxiCustomException extends RuntimeException{
    private ResponseMessage responseMessage;

    public TaxiCustomException(ResponseMessage responseMessage) {
        super(responseMessage.getValue());
        this.responseMessage = responseMessage;
    }
}
