package intelligent_taxi.taxiservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.taxiservice.domain.QTaxi;
import intelligent_taxi.taxiservice.domain.Taxi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaxiRepositoryImpl implements TaxiCustomRepository {

    private final JPAQueryFactory queryFactory;
    QTaxi taxi = QTaxi.taxi;

    public Taxi findOneById(Long id) {
        return queryFactory
                .selectFrom(taxi)
                .where(taxi.id.eq(id))
                .fetchOne();
    }
}
