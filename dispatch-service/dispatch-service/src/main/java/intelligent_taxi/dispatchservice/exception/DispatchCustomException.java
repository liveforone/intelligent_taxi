package intelligent_taxi.dispatchservice.exception;

import intelligent_taxi.dispatchservice.controller.restResponse.ResponseMessage;
import lombok.Getter;

@Getter
public class DispatchCustomException extends RuntimeException{
    private ResponseMessage responseMessage;

    public DispatchCustomException(ResponseMessage responseMessage) {
        super(responseMessage.getValue());
        this.responseMessage = responseMessage;
    }
}
