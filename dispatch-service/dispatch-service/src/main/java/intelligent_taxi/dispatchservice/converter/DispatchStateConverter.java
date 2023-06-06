package intelligent_taxi.dispatchservice.converter;

import intelligent_taxi.dispatchservice.domain.DispatchState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DispatchStateConverter implements AttributeConverter<DispatchState, String> {
    @Override
    public String convertToDatabaseColumn(DispatchState attribute) {
        return attribute.name();
    }

    @Override
    public DispatchState convertToEntityAttribute(String dbData) {
        return DispatchState.valueOf(dbData);
    }
}
