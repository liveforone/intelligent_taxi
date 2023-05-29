package intelligent_taxi.userservice.command;

import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.domain.Role;
import intelligent_taxi.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return createUserDetails(memberRepository.findByUsername(email));
    }

    private UserDetails createUserDetails(Member member) {
        if (member.getAuth() == Role.TAXI) {
            String TAXI_ROLE = "TAXI";
            return User.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .roles(TAXI_ROLE)
                    .build();
        } else {
            String MEMBER_ROLE = "MEMBER";
            return User.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .roles(MEMBER_ROLE)
                    .build();
        }
    }
}
