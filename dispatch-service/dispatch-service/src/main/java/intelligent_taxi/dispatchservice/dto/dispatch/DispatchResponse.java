package intelligent_taxi.dispatchservice.dto.dispatch;

import intelligent_taxi.dispatchservice.domain.DispatchState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchResponse {

    private Long id;
    private Double presentLatitude;
    private Double presentLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;
    private Double distance;
    private long price;
    private DispatchState dispatchState;
    private LocalDateTime createdDate;
}
