package intelligent_taxi.taxiservice.domain;

import intelligent_taxi.taxiservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.taxiservice.exception.TaxiCustomException;

public enum TaxiGrade {
    NORMAL, PREMIUM, VAN;

    public static String matchGrade(String grade) {
        for (TaxiGrade taxiGrade : TaxiGrade.values()) {
            if (taxiGrade.name().equalsIgnoreCase(grade)) {
                return taxiGrade.name();
            }
        }
        throw new TaxiCustomException(ResponseMessage.NOT_EXIST_GRADE);
    }
}
