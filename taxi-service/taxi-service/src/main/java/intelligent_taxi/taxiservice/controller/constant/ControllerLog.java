package intelligent_taxi.taxiservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    CREATE_TAXI_SUCCESS("Taxi Create Success!!"),
    UPDATE_REGION_SUCCESS("Update Region Success | ID : "),
    UPDATE_GRADE_SUCCESS("Update Grade Success | ID : "),
    DELETE_TAXI_SUCCESS("Delete Taxi Success | ID : ");

    private final String log;
}
