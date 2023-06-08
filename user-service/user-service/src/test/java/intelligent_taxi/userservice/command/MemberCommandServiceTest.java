package intelligent_taxi.userservice.command;

import intelligent_taxi.userservice.domain.MemberState;
import intelligent_taxi.userservice.domain.Role;
import intelligent_taxi.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_taxi.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_taxi.userservice.dto.response.MemberResponse;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_taxi.userservice.jwt.TokenInfo;
import intelligent_taxi.userservice.query.MemberQueryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberCommandServiceTest {

    @Autowired
    MemberCommandService memberCommandService;

    @Autowired
    MemberQueryService memberQueryService;

    @Autowired
    EntityManager em;

    String createMember(String email, String password, String realName, String bankbookNum) {
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        return memberCommandService.signupMember(memberSignupRequest);
    }

    @Test
    @Transactional
    void signupMemberTest() {
        //given
        String email = "member1111@gmail.com";
        String password = "1111";
        String realName = "test_member";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);

        //when
        String username = memberCommandService.signupMember(memberSignupRequest);
        em.flush();
        em.clear();

        //then
        MemberResponse member = memberQueryService.getMemberByUsername(username);
        assertThat(member.getRealName())
                .isEqualTo(realName);
        assertThat(member.getAuth())
                .isEqualTo(Role.MEMBER);
    }

    @Test
    @Transactional
    void signupTaxiTest() {
        //given
        String email = "member2222@gmail.com";
        String password = "2222";
        String realName = "test_taxi";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);

        //when
        String username = memberCommandService.signupTaxi(memberSignupRequest);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username).getRealName())
                .isEqualTo(realName);
    }

    @Test
    @Transactional
    void updateEmailTest() {
        //given
        String email = "abcd1234@gmail.com";
        String password = "1234";
        String realName = "test_email";
        String bankbookNum = "1234567891234";
        String username = createMember(email, password, realName, bankbookNum);
        em.flush();
        em.clear();

        //when
        String updatedEmail = "zzzz1111@gmail.com";
        ChangeEmailRequest request = new ChangeEmailRequest();
        request.setEmail(updatedEmail);
        memberCommandService.updateEmail(request, username);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username).getEmail())
                .isEqualTo(updatedEmail);
    }

    @Test
    @Transactional
    void updatePasswordTest() {
        //given
        String email = "password112423@gmail.com";
        String password = "1234";
        String realName = "test_password";
        String bankbookNum = "1234567891234";
        String username = createMember(email, password, realName, bankbookNum);
        em.flush();
        em.clear();

        //when
        String updatedPassword = "9999";
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setOldPassword(password);
        request.setNewPassword(updatedPassword);
        memberCommandService.updatePassword(request, username);
        em.flush();
        em.clear();

        //then
        MemberLoginRequest loginRequest = new MemberLoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(updatedPassword);
        TokenInfo token = memberCommandService.login(loginRequest);
        assertThat(token).isNotNull();
    }

    @Test
    @Transactional
    void increaseReportTest() {
        //given
        String email = "member3333@gmail.com";
        String password = "3333";
        String realName = "test_report";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        String username = memberCommandService.signupMember(memberSignupRequest);
        em.flush();
        em.clear();

        //when
        for (int i = 0; i < 10; i++) {
            memberCommandService.increaseReport(username);
        }
        em.flush();
        em.clear();

        //then
        MemberResponse member = memberQueryService.getMemberByUsername(username);
        assertThat(member.getMemberState())
                .isEqualTo(MemberState.BLOCK);
    }

    @Test
    @Transactional
    void cancelBlockTest() {
        //given
        String email = "member4444@gmail.com";
        String password = "4444";
        String realName = "test_cancel_block";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        String username = memberCommandService.signupMember(memberSignupRequest);
        em.flush();
        em.clear();

        for (int i = 0; i < 10; i++) {
            memberCommandService.increaseReport(username);
        }
        em.flush();
        em.clear();

        //when
        memberCommandService.cancelBlock(username);
        em.flush();
        em.clear();

        //then
        MemberResponse member = memberQueryService.getMemberByUsername(username);
        assertThat(member.getMemberState())
                .isEqualTo(MemberState.WORK);
    }
}