package intelligent_taxi.dispatchservice.dto.pay;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckBalanceRequest {

    private String bankbookNum;
    private long price;
}
