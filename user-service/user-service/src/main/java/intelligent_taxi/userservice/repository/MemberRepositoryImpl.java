package intelligent_taxi.userservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.userservice.domain.Member;
import intelligent_taxi.userservice.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    public Long findIdByEmail(String email) {
        return queryFactory
                .select(member.id)
                .from(member)
                .where(member.email.eq(email))
                .fetchOne();
    }

    public String findPasswordByUsername(String username) {
        return queryFactory
                .select(member.password)
                .from(member)
                .where(member.username.eq(username))
                .fetchOne();
    }

    public String findBankbookNumByUsername(String username) {
        return queryFactory
                .select(member.bankbookNum)
                .from(member)
                .where(member.username.eq(username))
                .fetchOne();
    }

    public Member findByUsername(String username) {
        return queryFactory.selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne();
    }

    public Member findByEmail(String email) {
        return queryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();
    }
}