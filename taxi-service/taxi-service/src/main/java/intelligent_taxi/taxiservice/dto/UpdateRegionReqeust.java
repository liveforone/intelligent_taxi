package intelligent_taxi.taxiservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRegionReqeust {

    @NotBlank(message = "지역을 선택하세요.")
    private String region;
}
