package intelligent_taxi.dispatchservice.repository;

import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_taxi.dispatchservice.domain.Dispatch;
import intelligent_taxi.dispatchservice.domain.QDispatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.querydsl.core.types.dsl.Expressions.*;
import static com.querydsl.core.types.dsl.MathExpressions.*;

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

    public Dispatch findOneWithinDistance(Double latitude, Double longitude) {
        return queryFactory
                .selectFrom(dispatch)
                .where(dispatch.presentLatitude.between(latitude - 0.009, latitude + 0.009)
                        .and(dispatch.presentLongitude.between(longitude - 0.009, longitude + 0.009))
                        .or(calculateDistance(latitude, longitude, dispatch.presentLatitude, dispatch.presentLongitude).loe(3.0))
                        .or(calculateDistance(latitude, longitude, dispatch.presentLatitude, dispatch.presentLongitude).loe(5.0))
                )
                .limit(1)
                .fetchOne();
    }

    private NumberExpression<Double> calculateDistance(Double lat1, Double lon1, NumberExpression<Double> lat2, NumberExpression<Double> lon2) {
        double lat1Rad = lat1 * Math.PI / 180.0;
        double lon1Rad = lon1 * Math.PI / 180.0;

        NumberTemplate<Double> lat1Num = numberTemplate(Double.class, "{0}", lat1Rad);
        NumberExpression<Double> lat2Rad = lat2.multiply(Math.PI).divide(180.0);
        NumberExpression<Double> lon2Rad = lon2.multiply(Math.PI).divide(180.0);

        NumberExpression<Double> lonDiff = lon2Rad.subtract(lon1Rad);

        NumberExpression<Double> a = sin(lat1Num).multiply(sin(lat2Rad))
                .add(cos(lat1Num).multiply(cos(lat2Rad)).multiply(cos(lonDiff)));

        return acos(a).multiply(6371.0);
    }
}
