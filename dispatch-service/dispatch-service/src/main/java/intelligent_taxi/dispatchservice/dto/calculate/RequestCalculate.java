package intelligent_taxi.dispatchservice.dto.calculate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCalculate {
    private Long dispatchId;
    private long price;
    private double distance;
}
