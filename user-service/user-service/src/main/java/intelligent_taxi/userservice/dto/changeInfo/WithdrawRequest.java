package intelligent_taxi.userservice.dto.changeInfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawRequest {

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;
}
