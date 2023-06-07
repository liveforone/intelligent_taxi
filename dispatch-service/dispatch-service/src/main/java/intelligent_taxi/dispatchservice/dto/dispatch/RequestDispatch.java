package intelligent_taxi.dispatchservice.dto.dispatch;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDispatch {

    @Max(value = 90, message = "위도의 최댓값은 90입니다.")
    @Min(value = -90, message = "위도의 최솟값은 -90입니다.")
    private Double presentLatitude;

    @Max(value = 180, message = "경도의 최댓값은 180입니다.")
    @Min(value = -180, message = "경도의 최솟값은 -180입니다.")
    private Double presentLongitude;
}
