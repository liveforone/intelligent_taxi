package intelligent_taxi.userservice.exception;

import intelligent_taxi.userservice.controller.restResponse.ResponseMessage;
import lombok.Getter;

@Getter
public class MemberCustomException extends RuntimeException{
    private ResponseMessage responseMessage;

    public MemberCustomException(ResponseMessage responseMessage) {
        super(responseMessage.getValue());
        this.responseMessage = responseMessage;
    }
}
