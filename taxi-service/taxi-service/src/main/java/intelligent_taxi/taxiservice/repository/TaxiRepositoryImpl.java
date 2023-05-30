package intelligent_taxi.taxiservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.taxiservice.domain.QTaxi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TaxiRepositoryImpl implements TaxiCustomRepository {
    private final JPAQueryFactory queryFactory;
    QTaxi taxi = QTaxi.taxi;
}
