package intelligent_taxi.userservice.config;

import intelligent_taxi.userservice.controller.constant.MemberUrl;
import intelligent_taxi.userservice.jwt.JwtAuthenticationFilter;
import intelligent_taxi.userservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(
                        MemberUrl.SIGNUP_MEMBER,
                        MemberUrl.SIGNUP_TAXI,
                        MemberUrl.LOGIN,
                        MemberUrl.MY_BANKBOOK_NUM,
                        MemberUrl.ACTUATOR
                ).permitAll()
                .anyRequest().authenticated()
        );
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
        http.exceptionHandling((exception) -> exception.accessDeniedPage(MemberUrl.PROHIBITION));
        return http.build();
    }
}
