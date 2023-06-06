package intelligent_taxi.dispatchservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.domain.QDispatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryImpl implements DispatchCustomRepository {

    private final JPAQueryFactory queryFactory;
    QDispatch dispatch = QDispatch.dispatch;

    public Dispatch findOneById(Long id) {
        return queryFactory
                .selectFrom(dispatch)
                .where(dispatch.id.eq(id))
                .fetchOne();
    }

    public Dispatch findCurrentOneByUsername(String username) {
        return queryFactory
                .selectFrom(dispatch)
                .where(dispatch.username.eq(username))
                .orderBy(dispatch.id.desc())
                .limit(1)
                .fetchOne();
    }
}
