package intelligent_taxi.userservice.repository;

import intelligent_taxi.userservice.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    String findBankbookNumByUsername(String username);
    Optional<Member> findByUsername(String username);
    Optional<Member> findByEmail(String email);
}
