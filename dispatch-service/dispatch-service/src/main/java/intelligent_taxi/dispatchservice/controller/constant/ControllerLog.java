package intelligent_taxi.dispatchservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    CREATE_DISPATCH_SUCCESS("Create Dispatch Success"),
    REMOVE_DISPATCH_SUCCESS("Remove Dispatch Success | ID : "),
    REQUEST_DISPATCH_SUCCESS("Request Dispatch Success | ID : ");

    private final String log;
}
