package intelligent_taxi.taxiservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateGradeRequest {

    @NotBlank(message = "택시 등급을 선택하세요.")
    private String taxiGrade;
}
