package intelligent_taxi.taxiservice.dto;

import intelligent_taxi.taxiservice.domain.TaxiGrade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaxiRequest {

    @NotBlank(message = "지역을 선택하세요.")
    private String region;

    @NotBlank(message = "택시 등급을 선택하세요.")
    private String taxiGrade;

    @NotBlank(message = "차량 번호를 입력하세요.(번호판 참조)")
    private String licenseNum;

    @NotBlank(message = "전화번호를 입력하세요.")
    @Size(min = 11, max = 11, message = "전화번호 양식이 맞지 않습니다.")
    private String phoneNum;
}
