package intelligent_taxi.userservice.dto.response;

import intelligent_taxi.userservice.domain.MemberState;
import intelligent_taxi.userservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String email;
    private String realName;
    private Role auth;
    private MemberState memberState;
}
