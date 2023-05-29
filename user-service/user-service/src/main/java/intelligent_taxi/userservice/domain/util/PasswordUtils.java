package intelligent_taxi.userservice.domain.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean isMatchPassword(String inputPw, String originalPw) {
        return passwordEncoder.matches(inputPw, originalPw);
    }
}
