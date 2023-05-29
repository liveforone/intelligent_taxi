package intelligent_taxi.userservice.repository;

import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.dto.response.MemberResponse;

import java.util.List;

public interface MemberRepositoryCustom {

    Long findIdByEmail(String email);
    String findPasswordByUsername(String username);
    String findBankbookNumByUsername(String username);
    Member findByUsername(String username);
    Member findByEmail(String email);
}
