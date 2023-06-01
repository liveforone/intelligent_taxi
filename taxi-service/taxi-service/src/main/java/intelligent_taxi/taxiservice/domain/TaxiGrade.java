package intelligent_taxi.taxiservice.domain;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;

public enum TaxiGrade {
    NORMAL, PREMIUM, VAN;

    public static TaxiGrade matchGrade(String grade) {
        for (TaxiGrade taxiGrade : TaxiGrade.values()) {
            if (taxiGrade.name().equalsIgnoreCase(grade)) {
                return taxiGrade;
            }
        }
        throw new TaxiCustomException(ResponseMessage.NOT_EXIST_GRADE);
    }
}
