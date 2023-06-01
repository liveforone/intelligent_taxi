package intelligent_taxi.taxiservice.converter;

import intelligent_taxi.taxiservice.domain.TaxiGrade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TaxiGradeConverter implements AttributeConverter<TaxiGrade, String> {
    @Override
    public String convertToDatabaseColumn(TaxiGrade attribute) {
        return attribute.name();
    }

    @Override
    public TaxiGrade convertToEntityAttribute(String dbData) {
        return TaxiGrade.valueOf(dbData);
    }
}
