package intelligent_taxi.userservice.query.util;

import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.dto.response.MemberResponse;
import intelligent_taxi.userservice.utility.CommonUtils;

public class MemberMapper {

    public static MemberResponse entityToDto(Member member) {
        if (CommonUtils.isNull(member)) {
            return new MemberResponse();
        }

        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .realName(member.getRealName())
                .auth(member.getAuth())
                .memberState(member.getMemberState())
                .build();
    }
}
