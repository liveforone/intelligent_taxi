package intelligent_taxi.userservice.converter;

import intelligent_taxi.userservice.domain.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.name();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.valueOf(dbData);
    }
}
