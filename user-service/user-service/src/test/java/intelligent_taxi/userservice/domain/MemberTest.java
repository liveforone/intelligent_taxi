package intelligent_taxi.userservice.domain;

import intelligent_taxi.userservice.domain.util.MemberBlockPolicy;
import intelligent_taxi.userservice.domain.util.PasswordUtils;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberSignupRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        //given
        String email = "createMember@gmail.com";
        String password = "1234";
        String realName = "test_create_member";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);

        //when
        Member member = Member.createMember(memberSignupRequest);

        //then
        assertThat(member.getAuth())
                .isEqualTo(Role.MEMBER);
    }

    @Test
    void createTaxi() {
        //given
        String email = "createTaxi@gmail.com";
        String password = "1234";
        String realName = "test_create_taxi";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);

        //when
        Member member = Member.createTaxi(memberSignupRequest);

        //then
        assertThat(member.getAuth())
                .isEqualTo(Role.TAXI);
    }

    @Test
    void updateEmail() {
        //given
        String email = "updateEmail@gmail.com";
        String password = "1234";
        String realName = "test_update_email";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        Member member = Member.createMember(memberSignupRequest);

        //when
        String newEmail = "updated@naver.com";
        member.updateEmail(newEmail);

        //then
        assertThat(member.getEmail())
                .isEqualTo(newEmail);
    }

    @Test
    void updatePassword() {
        //given
        String email = "updatePassword@gmail.com";
        String password = "1234";
        String realName = "test_update_password";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        Member member = Member.createMember(memberSignupRequest);

        //when
        String newPassword = "123456789";
        member.updatePassword(newPassword);

        //then
        assertThat(PasswordUtils.isMatchPassword(newPassword, member.getPassword()))
                .isTrue();
    }

    @Test
    void increaseReport() {
        //given
        String email = "updatePassword@gmail.com";
        String password = "1234";
        String realName = "test_update_password";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        Member member = Member.createMember(memberSignupRequest);

        //when
        for (int i = 0; i < 10; i++) {
            member.increaseReport();
        }

        //then
        assertThat(member.getMemberState())
                .isEqualTo(MemberState.BLOCK);
        assertThat(member.getReports())
                .isEqualTo(10);
    }

    @Test
    void cancelBlock() {
        //given
        String email = "updatePassword@gmail.com";
        String password = "1234";
        String realName = "test_update_password";
        String bankbookNum = "1234567891234";
        MemberSignupRequest memberSignupRequest = new MemberSignupRequest();
        memberSignupRequest.setEmail(email);
        memberSignupRequest.setPassword(password);
        memberSignupRequest.setRealName(realName);
        memberSignupRequest.setBankbookNum(bankbookNum);
        Member member = Member.createMember(memberSignupRequest);

        for (int i = 0; i < 10; i++) {
            member.increaseReport();
        }

        //when
        member.cancelBlock();

        //then
        assertThat(member.getMemberState())
                .isEqualTo(MemberState.WORK);
        assertThat(member.getReports())
                .isEqualTo(MemberBlockPolicy.BLOCK_CHECK_REPORT);
    }
}