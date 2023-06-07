package intelligent_taxi.userservice.validator;

import intelligent_taxi.userservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.exception.MemberCustomException;
import intelligent_taxi.userservice.repository.MemberRepository;
import intelligent_taxi.userservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceValidator {

    private final MemberRepository memberRepository;

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void validateDuplicateEmail(String email) {
        Long foundId = memberRepository.findIdByEmail(email);

        if (!CommonUtils.isNull(foundId)) {
            throw new MemberCustomException(ResponseMessage.DUPLICATE_EMAIL);
        }
    }

    public Member validatePassword(String inputPassword, String username) {
        Member member = memberRepository.findByUsername(username);

        if (!passwordEncoder.matches(inputPassword, member.getPassword())) {
            throw new MemberCustomException(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        return member;
    }
}
