package intelligent_taxi.dispatchservice.domain;

import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DispatchTest {

    @Test
    void createTest() {
        //given
        Double presentLatitude = 37.582848;
        Double presentLongitude = 127.010581;
        Double destinationLatitude = 37.579369;
        Double destinationLongitude = 127.015299;
        String username = "dslfjnlsfldsjfalkdfja";
        DispatchRequest request = new DispatchRequest();
        request.setPresentLatitude(presentLatitude);
        request.setPresentLongitude(presentLongitude);
        request.setDestinationLatitude(destinationLatitude);
        request.setDestinationLongitude(destinationLongitude);

        //when
        Dispatch dispatch = Dispatch.create(request, username);

        //then
        assertThat(dispatch.getDispatchState())
                .isEqualTo(DispatchState.READY);
    }
}