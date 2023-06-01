package intelligent_taxi.userservice.converter;

import intelligent_taxi.userservice.domain.MemberState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MemberStateConverter implements AttributeConverter<MemberState, String> {
    @Override
    public String convertToDatabaseColumn(MemberState attribute) {
        return attribute.name();
    }

    @Override
    public MemberState convertToEntityAttribute(String dbData) {
        return MemberState.valueOf(dbData);
    }
}
