package intelligent_taxi.dispatchservice.dto.taxi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxiResponse {

    private Long id;
    private String region;
    private TaxiGrade taxiGrade;
    private String licenseNum;
    private String phoneNum;
}
