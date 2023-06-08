package intelligent_taxi.userservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    public String findBankbookNumByUsername(String username) {
        return queryFactory
                .select(member.bankbookNum)
                .from(member)
                .where(member.username.eq(username))
                .fetchOne();
    }

    public Optional<Member> findByUsername(String username) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne());
    }

    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne());
    }
}