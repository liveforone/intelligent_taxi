package intelligent_taxi.dispatchservice.domain.policy;

import intelligent_taxi.dispatchservice.distanceAlgorithm.DistanceCalculator;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;

public class PriceCalculator {

    private final static double BASIC = 2.0;
    private final static double ADDITION = 0.12;
    private final static long BASIC_PRICE = 4000;
    private final static long ADDITION_PRICE = 100;

    public static long calculatePrice(DispatchRequest requestDto) {
        double calculatedDistance = DistanceCalculator.calculateDistance(
                requestDto.getPresentLatitude(),
                requestDto.getPresentLongitude(),
                requestDto.getDestinationLatitude(),
                requestDto.getPresentLongitude()
        );

        if (calculatedDistance <= BASIC) {
            return BASIC_PRICE;
        } else {
            double restDistance = calculatedDistance - BASIC;
            return BASIC_PRICE + (long) (restDistance / ADDITION) * ADDITION_PRICE;
        }
    }
}
