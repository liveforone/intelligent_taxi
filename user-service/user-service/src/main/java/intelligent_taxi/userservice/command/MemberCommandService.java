package intelligent_taxi.userservice.command;

import intelligent_taxi.userservice.controller.restResponse.ResponseMessage;
import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_taxi.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_taxi.userservice.exception.MemberCustomException;
import intelligent_taxi.userservice.jwt.JwtTokenProvider;
import intelligent_taxi.userservice.jwt.TokenInfo;
import intelligent_taxi.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public String signupMember(MemberSignupRequest requestDto) {
        Member member = Member.createMember(requestDto);
        return memberRepository.save(member).getUsername();
    }

    public String signupTaxi(MemberSignupRequest requestDto) {
        Member member = Member.createTaxi(requestDto);
        return memberRepository.save(member).getUsername();
    }

    public TokenInfo login(MemberLoginRequest requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        String username = member.getUsername();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        return jwtTokenProvider
                .generateToken(authentication);
    }

    public void updateEmail(ChangeEmailRequest requestDto, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.updateEmail(requestDto.getEmail());
    }

    public void updatePassword(ChangePasswordRequest requestDto, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.updatePassword(requestDto.getNewPassword(), requestDto.getOldPassword());
    }

    public void increaseReport(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.increaseReport();
    }

    public void cancelBlock(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.cancelBlock();
    }

    public void withdrawByUsername(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));

        if (!username.equals(member.getUsername())) {
            throw new MemberCustomException(ResponseMessage.USERNAME_NOT_MATCH);
        }

        memberRepository.delete(member);
    }
}
