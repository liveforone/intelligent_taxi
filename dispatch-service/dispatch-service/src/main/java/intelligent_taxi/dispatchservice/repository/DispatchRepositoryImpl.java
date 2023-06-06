package intelligent_taxi.dispatchservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryImpl implements DispatchCustomRepository {

    private final JPAQueryFactory queryFactory;
}
