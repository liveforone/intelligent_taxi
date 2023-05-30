package intelligent_taxi.taxiservice.dto;

import intelligent_taxi.taxiservice.domain.Region;
import intelligent_taxi.taxiservice.domain.TaxiGrade;
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
    private String taxiGrade;
    private String licenseNum;
    private String phoneNum;
}
