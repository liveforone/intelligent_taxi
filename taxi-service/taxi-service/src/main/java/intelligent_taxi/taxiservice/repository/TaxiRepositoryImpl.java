package intelligent_taxi.taxiservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.taxiservice.domain.QTaxi;
import intelligent_taxi.taxiservice.domain.Taxi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaxiRepositoryImpl implements TaxiCustomRepository {

    private final JPAQueryFactory queryFactory;
    QTaxi taxi = QTaxi.taxi;

    public Optional<Taxi> findOneById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(taxi)
                .where(taxi.id.eq(id))
                .fetchOne());
    }

    public Optional<Taxi> findOneByUsername(String username) {
        return Optional.ofNullable(queryFactory
                .selectFrom(taxi)
                .where(taxi.username.eq(username))
                .fetchOne());
    }
}
