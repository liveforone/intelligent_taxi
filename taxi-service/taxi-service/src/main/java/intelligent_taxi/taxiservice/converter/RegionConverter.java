package intelligent_taxi.taxiservice.converter;

import intelligent_taxi.taxiservice.domain.Region;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RegionConverter implements AttributeConverter<Region, String> {
    @Override
    public String convertToDatabaseColumn(Region attribute) {
        return attribute.name();
    }

    @Override
    public Region convertToEntityAttribute(String dbData) {
        return Region.valueOf(dbData);
    }
}
