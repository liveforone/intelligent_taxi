package intelligent_taxi.dispatchservice.command;

import intelligent_taxi.dispatchservice.distanceAlgorithm.DistanceCalculator;
import intelligent_taxi.dispatchservice.domain.DispatchState;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchRequest;
import intelligent_taxi.dispatchservice.dto.dispatch.DispatchResponse;
import intelligent_taxi.dispatchservice.dto.dispatch.RequestDispatch;
import intelligent_taxi.dispatchservice.query.DispatchQueryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class DispatchCommandServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    DispatchCommandService dispatchCommandService;

    @Autowired
    DispatchQueryService dispatchQueryService;

    Long createDispatch(String username) {
        Double presentLatitude = 37.582848;
        Double presentLongitude = 127.010581;
        Double destinationLatitude = 37.579369;
        Double destinationLongitude = 127.015299;
        DispatchRequest request = new DispatchRequest();
        request.setPresentLatitude(presentLatitude);
        request.setPresentLongitude(presentLongitude);
        request.setDestinationLatitude(destinationLatitude);
        request.setDestinationLongitude(destinationLongitude);
        return dispatchCommandService.createDispatch(request, username);
    }

    @Test
    @Transactional
    void createDispatchTest() {
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
        Long dispatchId = dispatchCommandService.createDispatch(request, username);
        em.flush();
        em.clear();

        //then
        DispatchResponse dispatch = dispatchQueryService.getDispatchById(dispatchId);
        assertThat(dispatch.getDispatchState())
                .isEqualTo(DispatchState.READY);
        assertThat(dispatch.getDistance())
                .isEqualTo(DistanceCalculator.calculateDistance(presentLatitude, presentLongitude, destinationLatitude, destinationLongitude));
    }

    @Test
    @Transactional
    void removeDispatchTest() {
        //given
        String username = "wofjwojfofjo";
        Long dispatchId = createDispatch(username);
        em.flush();
        em.clear();

        //when
        dispatchCommandService.removeDispatch(dispatchId, username);
        em.flush();
        em.clear();

        //then
        assertThat(dispatchQueryService.getDispatchById(dispatchId).getId())
                .isNull();
    }

    @Test
    @Transactional
    void rollbackDispatchTest() {
        //given
        String username = "pppppppppppppp";
        Long dispatchId = createDispatch(username);
        em.flush();
        em.clear();

        //when
        dispatchCommandService.rollbackDispatch(dispatchId);
        em.flush();
        em.clear();

        //then
        assertThat(dispatchQueryService.getDispatchById(dispatchId).getId())
                .isNull();
    }

    @Test
    @Transactional
    void dispatchTest() {
        //given
        String memberUsername = "pppppppppppppp";
        Long dispatchId = createDispatch(memberUsername);
        em.flush();
        em.clear();
        String taxiUsername = "dfnosenfonfosnfesf";
        Double presentLatitude = 37.588374;
        Double presentLongitude = 127.005907;
        RequestDispatch requestDto = new RequestDispatch();
        requestDto.setPresentLatitude(presentLatitude);
        requestDto.setPresentLongitude(presentLongitude);

        //when
        dispatchCommandService.dispatch(requestDto, taxiUsername);
        em.flush();
        em.clear();

        //then
        DispatchResponse dispatch = dispatchQueryService.getDispatchById(dispatchId);
        assertThat(dispatch.getDispatchState())
                .isEqualTo(DispatchState.FINISH);
    }
}