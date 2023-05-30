package intelligent_taxi.userservice.domain;

import intelligent_taxi.userservice.domain.util.MemberBlockPolicy;
import intelligent_taxi.userservice.domain.util.MemberConstant;
import intelligent_taxi.userservice.domain.util.PasswordUtils;
import intelligent_taxi.userservice.dto.signupAndLogin.MemberSignupRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private String bankbookNum;

    private long reports;

    @Column(nullable = false)
    private String auth;

    @Column(nullable = false)
    private String memberState;

    private Member(String username, String email, String password, String realName, String bankbookNum, String auth) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.realName = realName;
        this.bankbookNum = bankbookNum;
        this.reports = 0;
        this.auth = auth;
        this.memberState = MemberState.WORK.name();
    }

    public static Member createMember(MemberSignupRequest request) {
        return new Member(
                createUsername(),
                request.getEmail(),
                PasswordUtils.encodePassword(request.getPassword()),
                request.getRealName(),
                request.getBankbookNum(),
                Role.MEMBER.getAuth()
        );
    }

    public static Member createTaxi(MemberSignupRequest request) {
        return new Member(
                createUsername(),
                request.getEmail(),
                PasswordUtils.encodePassword(request.getPassword()),
                request.getRealName(),
                request.getBankbookNum(),
                Role.TAXI.getAuth()
        );
    }

    private static String createUsername() {
        return UUID.randomUUID() + RandomStringUtils.randomAlphabetic(MemberConstant.RANDOM_STRING_LENGTH);
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePassword(String password) {
        this.password = PasswordUtils.encodePassword(password);
    }

    public void increaseReport() {
        if (MemberBlockPolicy.BLOCK_CHECK_REPORT == this.getReports()) {
            this.memberState = MemberState.BLOCK.name();
        }
        this.reports += MemberConstant.ONE;
    }

    public void cancelBlock() {
        this.memberState = MemberState.WORK.name();
        this.reports -= MemberConstant.ONE;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(auth));
        return authList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
